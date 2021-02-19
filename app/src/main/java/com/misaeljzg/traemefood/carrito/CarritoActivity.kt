package com.misaeljzg.traemefood.carrito

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.roomdatabase.Orden
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO
import com.misaeljzg.traemefood.roomdatabase.OrdenDatabase
import com.stripe.android.ApiResultCallback
import com.stripe.android.PaymentIntentResult
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.StripeIntent
import com.stripe.android.view.CardInputWidget
import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

class CarritoActivity : AppCompatActivity(), CoroutineScope {
    //private val backendUrl = "http://1.1.1.10:4242/" Servidor local
    private val backendUrl = "https://stripe-payment-android-test.herokuapp.com/" //Servidor en Heroku
    private val httpClient = OkHttpClient()
    private lateinit var paymentIntentClientSecret : String
    private lateinit var stripe : Stripe
    lateinit var database: OrdenDAO
    private var precioMulS : Int = 0
    private var precioDB : Int = 0
    private var nombreCli : String = ""
    private var correoCli : String = ""
    private var totalTv : TextView? = null
    private var idRest: String? = null
    private var cancelar : Button? = null
    private var pagar : Button? = null
    private var job: Job = Job()


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        idRest = intent.getStringExtra("ID_Rest").toString()
        database = OrdenDatabase.getInstance(application).ordenDAO
        totalTv = findViewById(R.id.tv_carrito_total)
        cancelar = findViewById(R.id.btn_carrito_cancelar)
        pagar = findViewById(R.id.btn_carrito_pagar)
        val rv: RecyclerView = findViewById(R.id.rv_carrito)
        val cardInputWidget = findViewById<CardInputWidget>(R.id.cardInputWidget)

        rv.layoutManager = LinearLayoutManager(this)
        launch {
            val orders = database.getOrdenes(idRest!!)
            rv.adapter = CarritoAC(orders as MutableList<Orden>, coroutineContext, database)

            if (orders.isNotEmpty()){
                val total = database.getTotal(idRest!!)
                totalTv?.text =  total.toString()
                precioMulS = total.toInt() * 100
                precioDB = total.toInt()

                cardInputWidget.visibility = View.VISIBLE
                cancelar?.visibility = View.VISIBLE
                pagar?.visibility = View.VISIBLE
            } else{
                totalTv?.text = "0"
                fadeOut()
            }
            stripe = Stripe(applicationContext, "pk_test_51HxH2nGOApI9wW6huovpLtcPw8yXVmTEWirKgj1O5reFMVAeJ6ToUIQDjyexS7j4YxAeDy1tIIv6Ina4isGXFaAG005IxQyoeY")
            startCheckout()
        }
        cancelar?.setOnClickListener {
            launch {
                database.clear(idRest!!)
                val orders = database.getOrdenes(idRest!!)
                rv.adapter = CarritoAC(orders as MutableList<Orden>, coroutineContext, database)
            }
            totalTv?.text = "0"
            fadeOut()
            Toast.makeText(this, "Orden borrada", Toast.LENGTH_SHORT).show()
        }

        pagar?.setOnClickListener {
            //Aqui va lo del pago :)
            if (totalTv?.text.toString().toFloat() > 0){ //Si el valor está en 0, el botón de pagar no debería hacer nada
                Toast.makeText(this, "Procediendo al pago...", Toast.LENGTH_SHORT).show()

                cardInputWidget.paymentMethodCreateParams?.let { params ->
                    val confirmParams = ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                        params,paymentIntentClientSecret)
                    stripe.confirmPayment(this, confirmParams)
                }
            }else{
                Toast.makeText(this, "No hay elementos en la Orden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fadeOut(){
        if(totalTv?.text.toString().toInt() == 0){
            cardInputWidget.visibility = View.INVISIBLE
            cancelar?.visibility = View.INVISIBLE
            pagar?.visibility = View.INVISIBLE
        }
    }

    private fun startCheckout() {
        val weakActivity = WeakReference<Activity>(this)
        val mediaType = "application/json; charset=utf-8".toMediaType()

        val amount : Double = precioMulS.toDouble()
        val payMap = HashMap<String, Any>()
        val itemMap = HashMap<String, Any>()
        val itemList = ArrayList<HashMap<String, Any>>()
        payMap["currency"] = "MXN"
        itemMap["id"] = "1234"
        itemMap["name"] = "Algún nombre"
        itemMap["email"] = "Algún correo@gmail.com"
        itemMap["amount"] = amount
        itemList.add(itemMap)
        payMap["items"] = itemList
        val requestJson : String = Gson().toJson(payMap)
        val body = requestJson.toRequestBody(mediaType)
        val request = Request.Builder().url(backendUrl + "create-payment-intent").post(body).build()

        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                weakActivity.get()?.let { activity ->
                    displayAlert(activity, "Error al conectar con el servidor", "Error: $e")
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    weakActivity.get()?.let { activity ->
                        displayAlert(activity, "Error al conectar con el servidor", "Error: $response")
                    }
                } else {
                    val responseData = response.body?.string()
                    val responseJson = responseData?.let { JSONObject(it) } ?: JSONObject()
                    paymentIntentClientSecret = responseJson.getString("clientSecret")
                }
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val weakActivity = WeakReference<Activity>(this)

        stripe.onPaymentResult(requestCode, data, object : ApiResultCallback<PaymentIntentResult> {
            @SuppressLint("ShowToast")
            override fun onSuccess(result: PaymentIntentResult) {
                val paymentIntent = result.intent
                val status = paymentIntent.status

                if (status == StripeIntent.Status.Succeeded) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    weakActivity.get()?.let { activity ->
                        //displayAlert(activity, "Payment succeeded", gson.toJson(paymentIntent))
                        ordersToFirebase()
                    }
                } else if (status == StripeIntent.Status.RequiresPaymentMethod) {
                    weakActivity.get()?.let { activity ->
                        displayAlert(activity, "Payment failed required method", paymentIntent.lastPaymentError?.message.orEmpty())
                        //Toast.makeText(this@CheckoutActivity, "Pago fallido", Toast.LENGTH_LONG)
                    }
                }
            }
            override fun onError(e: Exception) {
                weakActivity.get()?.let { activity ->
                    displayAlert(activity, "Payment sin fondos", e.toString())
                }
            }
        })
    }

    private fun ordersToFirebase(){
        launch {
            val description = database.getOrdenes(idRest!!).toString()
            val refFB = FirebaseDatabase.getInstance().getReference("OrderDetails")
            val orderId = refFB.push().key
            val uidRef = FirebaseDatabase.getInstance().getReference("user")

            val valueEventListener = object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach { childSnapshot ->
                        val retrieveFB = childSnapshot.getValue(UserFB::class.java)
                        nombreCli  = retrieveFB?.nombre.toString()
                        correoCli = retrieveFB?.correo.toString()

                        val detalles = orderId?.let {
                            OrdenDetalle(it,
                                nombreCli,
                                correoCli,
                                description,
                                precioDB
                            )}

                        if (orderId != null) {
                            refFB.child(orderId).setValue(detalles).addOnCompleteListener {
                                Toast.makeText(this@CarritoActivity, "Stripe and Firebase successfully", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            }
            uidRef.addValueEventListener(valueEventListener)
        }
    }

    private fun displayAlert(activity: Activity, title: String, message: String, restartDemo: Boolean = false) {
        runOnUiThread {
            val builder = AlertDialog.Builder(activity).setTitle(title).setMessage(message)
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
    }

}
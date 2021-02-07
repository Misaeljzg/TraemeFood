package com.misaeljzg.traemefood.carrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.roomdatabase.Orden
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO
import com.misaeljzg.traemefood.roomdatabase.OrdenDatabase
import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CarritoActivity : AppCompatActivity(), CoroutineScope {

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
        val idRest: String = intent.getStringExtra("ID_Rest").toString()
        val rv: RecyclerView = findViewById(R.id.rv_carrito)
        val database: OrdenDAO = OrdenDatabase.getInstance(application).ordenDAO
        val cancelar: Button = findViewById(R.id.btn_carrito_cancelar)
        val pagar: Button = findViewById(R.id.btn_carrito_pagar)
        val totalTv: TextView = findViewById(R.id.tv_carrito_total)
        rv.layoutManager = LinearLayoutManager(this)
        launch {
            val orders = database.getOrdenes(idRest)
            rv.adapter = CarritoAC(orders as MutableList<Orden>, coroutineContext, database)

            if (orders.isNotEmpty()){
                val total = database.getTotal(idRest)
                totalTv.text =  total.toString()
            } else{
                totalTv.text = "0"
            }
        }
        cancelar.setOnClickListener {
            launch {
                database.clear(idRest!!)
                val orders = database.getOrdenes(idRest)
                rv.adapter = CarritoAC(orders as MutableList<Orden>, coroutineContext, database)
            }
            totalTv.text = "0"
            Toast.makeText(this, "Orden borrada", Toast.LENGTH_SHORT).show()
        }

        pagar.setOnClickListener {
            //Aqui va lo del pago :)
            if (totalTv.text.toString().toFloat() > 0){ //Si el valor está en 0, el botón de pagar no debería hacer nada
                Toast.makeText(this, "Procediendo al pago...", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "No hay elementos en la Orden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
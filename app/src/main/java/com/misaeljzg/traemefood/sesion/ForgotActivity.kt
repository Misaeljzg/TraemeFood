package com.misaeljzg.traemefood.sesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.misaeljzg.traemefood.R
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {

    private val  auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)


        btnsend.setOnClickListener {
            val correo = sendCorreo.text.toString()

            if (correo.isEmpty()){
                Toast.makeText(this,"Verifica la informacion del campo ",Toast.LENGTH_SHORT).show()
            }
        if(!TextUtils.isEmpty(correo))
            auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){

                            startActivity(Intent(this,LoginActivity::class.java))
                        }else{
                            android.app.AlertDialog.Builder(this).apply {
                                setTitle("Error al Recuperar Contraseña")
                                setMessage("Hay un Error al Encontrar el Usuario  Verifica  Usuario  O Vuelve a Intentarlo mas Tarde")

                                setPositiveButton("Aceptar", null)
                            }.show()
                        }
                    }
        }
    }
}
package com.misaeljzg.traemefood.sesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.misaeljzg.traemefood.MainActivity
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.chat.ChatLogActivity
import com.misaeljzg.traemefood.chat.LatestMessagesActivity
import com.misaeljzg.traemefood.chat.NewMessagerActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class LoginActivity : AppCompatActivity() {
    private val  auth  =    FirebaseAuth.getInstance()
    private  var miShowPass =  false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        MostarL()
        btnRegistro.setOnClickListener {
            Register()
        }
        btnLogin.setOnClickListener {
            performLogin()
        }
        olvidastec.setOnClickListener {
            Olvidar()
        }

    }
    private fun performLogin(){
        val correo=etLoginCorreo.text.toString()
        val password = etLoginPassword.text.toString()

        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por   favor Ingresa Tu correo o contraseña", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(correo , password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    val intent = Intent(this,MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    android.app.AlertDialog.Builder(this).apply {
                        setTitle("Error de Inicio")
                        setMessage("Hay un Error al Encontrar el Usuario  Verifica  Usuario y Contraseña  O Vuelve a Intentarlo mas Tarde")
                        setPositiveButton("Aceptar", null)
                    }.show()
                }

    }

    private fun Olvidar (){
        val intent = Intent (this,ForgotActivity::class.java)
        startActivity(intent)
    }
    private fun Register(){
        val intent = Intent (this,RegistroActivity::class.java)
        startActivity(intent)
    }

    private fun showPassword(isShow:Boolean){
if (isShow) {
    etLoginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
    PasswordReg.setImageResource(R.drawable.ic_baseline_visibility_24)
}else {
    etLoginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
    PasswordReg.setImageResource(R.drawable.ic_baseline_visibility_off_24)
}
        etLoginPassword.setSelection(etLoginPassword.text.toString().length)
    }


    private fun MostarL (){
        PasswordReg.setOnClickListener {
            miShowPass = !miShowPass
            showPassword( miShowPass)
        }
        showPassword(miShowPass)
    }



}
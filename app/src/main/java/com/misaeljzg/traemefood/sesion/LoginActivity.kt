package com.misaeljzg.traemefood.sesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.misaeljzg.traemefood.MainActivity
import com.misaeljzg.traemefood.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    private fun setListeners() {
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistro)
        var loginCorrecto: Boolean = true
        btnLogin.setOnClickListener {
            var emailEt: EditText = findViewById(R.id.etLoginCorreo)
            var passwordET: EditText = findViewById(R.id.etLoginPassword)
            var email: String = emailEt.text.toString()
            var password: String = passwordET.text.toString()
            if(loginCorrecto){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //loginCorrecto = false
            }else{
             Toast.makeText(this, "Login incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegistrar.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}
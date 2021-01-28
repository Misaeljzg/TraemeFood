package com.misaeljzg.traemefood.sesion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.misaeljzg.traemefood.R
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        registrar()
    }

    private fun registrar() {
        var enviarRegistro = findViewById<Button>(R.id.btnReg_send)
        enviarRegistro.setOnClickListener {
            var nombre = findViewById<EditText>(R.id.etReg_Nombre).text.toString()
            var apellidos = findViewById<EditText>(R.id.etReg_Apellidos).text.toString()
            var direccion = findViewById<EditText>(R.id.etReg_Direccion).text.toString()
            var email = findViewById<EditText>(R.id.etReg_Email).text.toString()
            var numero = findViewById<EditText>(R.id.et_Reg_Telefono).text.toString()
            var contrasena = findViewById<EditText>(R.id.et_Reg_Password).text.toString()
            var confcontrasena = findViewById<EditText>(R.id.et_Reg_Password_Conf).text.toString()
        }
    }
}
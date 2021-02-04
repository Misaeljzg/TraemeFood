package com.misaeljzg.traemefood.carrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.misaeljzg.traemefood.R

class CarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        val idRest = intent.getStringExtra("ID_Rest")
        val id: TextView = findViewById(R.id.tv_carrito_id_rest)
        id.text = idRest
    }
}
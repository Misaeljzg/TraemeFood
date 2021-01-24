package com.misaeljzg.traemefood.restaurantes

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.utils.Restaurante

class ACRestaurantes(items: MutableList<Restaurante>, context: Context) : RecyclerView.Adapter<ACRestaurantes.ViewHolder>(){
    private var items:MutableList<Restaurante> = ArrayList()
    var context: Context = context

    init{
        this.items = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_restaurantes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, context)

    }
    class ViewHolder(vista:View): RecyclerView.ViewHolder(vista){
        val nombre: TextView = vista.findViewById(R.id.tv_vistarestaurantes_NombreRestaurante)
        val imgpath: ImageView = vista.findViewById(R.id.iv_vistarestaurantes_foto)
        val descripcion: TextView = vista.findViewById(R.id.tv_vistarestaurantes_Descripcion)
        val direccion: TextView = vista.findViewById(R.id.tv_vistarestaurantes_Direccion)

        fun bind(restaurante: Restaurante, context: Context){
//            nombre.text = restaurante.
//            descripcion.text = restaurante.descripcion
//            itemView.setOnClickListener { Toast.makeText(context, restaurante.nombreREST, Toast.LENGTH_SHORT).show() }
        }

    }

}
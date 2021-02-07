package com.misaeljzg.traemefood.carrito

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.roomdatabase.Orden
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO
import kotlinx.android.synthetic.main.template_orden.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CarritoAC(private val allOrders: MutableList<Orden>, override val coroutineContext: CoroutineContext, val database:OrdenDAO) : ListAdapter<Orden, CarritoAC.CarritoVH>(DiffCallback), CoroutineScope {

    companion object DiffCallback : DiffUtil.ItemCallback<Orden>(){
        override fun areItemsTheSame(oldItem: Orden, newItem: Orden): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Orden, newItem: Orden): Boolean {
            return oldItem == newItem
        }
    }
    class CarritoVH(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_orden, parent,false)
        return CarritoVH(view)
    }

    override fun onBindViewHolder(holder: CarritoVH, position: Int) {
        holder.itemView.tv_orden_nombre_platillo.text = allOrders[position].nombrePlatillo
        holder.itemView.tv_orden_cantidad.text = allOrders[position].cantidadPlatillo.toString()
        holder.itemView.tv_orden_precio.text = allOrders[position].total.toString()
        holder.itemView.btn_orden_borrar.setOnClickListener {
            //deleteOrden(position)
        }
    }

    private fun deleteOrden(position: Int) {
        val item = allOrders[position]
        allOrders.remove(item)
         launch {
            database.deleteOrden(item)
        }
    }

    override fun getItemCount(): Int = allOrders.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
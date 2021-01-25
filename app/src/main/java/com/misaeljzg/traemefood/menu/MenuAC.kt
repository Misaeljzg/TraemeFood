package com.misaeljzg.traemefood.menu

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.databinding.TemplateMenuBinding
import com.misaeljzg.traemefood.utils.Platillo
import kotlinx.android.synthetic.main.template_menu.view.*

class MenuAC : ListAdapter<Platillo, MenuAC.MenuVH>(DiffCallback){

    class MenuVH (private var binding: TemplateMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(platillo: Platillo){
            binding.platillo = platillo
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Platillo>(){
        override fun areItemsTheSame(oldItem: Platillo, newItem: Platillo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Platillo, newItem: Platillo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuVH {
        return MenuVH(TemplateMenuBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MenuVH, position: Int) {
        val platillo = getItem(position)
        var add = holder.itemView.btn_add
        var mas = holder.itemView.btn_mas
        var menos = holder.itemView.btn_menos
        var cantidad = holder.itemView.tv_cantidad
        var ammount: Int = 0
        cantidad.text = ammount.toString()
        add.setOnClickListener{
            Log.i("clicked", position.toString())
        }
        mas.setOnClickListener {
            ammount+=1
            cantidad.text = ammount.toString()
        }
        menos.setOnClickListener {
            if(ammount>0) {
                ammount -= 1
            }
            else{
                ammount = 0
            }
            cantidad.text = ammount.toString()
        }
        holder.bind(platillo)
    }
}

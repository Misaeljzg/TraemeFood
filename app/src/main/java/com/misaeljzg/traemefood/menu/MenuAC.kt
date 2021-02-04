package com.misaeljzg.traemefood.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.databinding.TemplateMenuBinding
import com.misaeljzg.traemefood.restaurantes.RestaurantesAC
import com.misaeljzg.traemefood.utils.Platillo
import com.misaeljzg.traemefood.utils.Restaurante
import kotlinx.android.synthetic.main.template_menu.view.*

class MenuAC(private val onClickListener: OnClickListener) : ListAdapter<Platillo, MenuAC.MenuVH>(DiffCallback){

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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(platillo)
        }
        holder.bind(platillo)
    }
    class OnClickListener(val clickListener: (platillo: Platillo) -> Unit){
        fun onClick(platillo: Platillo) = clickListener(platillo)
    }

}
package com.misaeljzg.traemefood.restaurantes


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.databinding.TemplateRestaurantesBinding
import com.misaeljzg.traemefood.utils.Restaurante

class RestaurantesAC(private val onClickListener: OnClickListener) : ListAdapter<Restaurante, RestaurantesAC.RestauranteViewHolder>(DiffCallback) {

    class RestauranteViewHolder(private var binding: TemplateRestaurantesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(restaurante: Restaurante){
            binding.restaurante = restaurante
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Restaurante>(){
        override fun areItemsTheSame(oldItem: Restaurante, newItem: Restaurante): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Restaurante, newItem: Restaurante): Boolean {
            return oldItem.idRes == newItem.idRes
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantesAC.RestauranteViewHolder {
        return RestauranteViewHolder(TemplateRestaurantesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val restaurante = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(restaurante)
        }
        holder.bind(restaurante)
    }
    class OnClickListener(val clickListener: (restaurante:Restaurante) -> Unit){
        fun onClick(restaurante: Restaurante) = clickListener(restaurante)
    }
}

package com.misaeljzg.traemefood

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.misaeljzg.traemefood.menu.MenuAC
import com.misaeljzg.traemefood.restaurantes.RestaurantesAC
import com.misaeljzg.traemefood.restaurantes.RestaurantesVM
import com.misaeljzg.traemefood.utils.Platillo
import com.misaeljzg.traemefood.utils.Restaurante

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Restaurante>?){
    val adapter = recyclerView.adapter as RestaurantesAC
    adapter.submitList(data)
}

@BindingAdapter("listMenu")
fun bindMenuView(recyclerView: RecyclerView, data: List<Platillo>?){
    val adapter = recyclerView.adapter as MenuAC
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter ("ApiStatus")
fun bindStatus(statusIV : ImageView, status: RestaurantesVM.ApiStatus){
    when(status){
        RestaurantesVM.ApiStatus.CARGANDO ->{
            statusIV.visibility = View.VISIBLE
            statusIV.setImageResource(R.drawable.loading_animation)
        }
        RestaurantesVM.ApiStatus.ERROR -> {
            statusIV.visibility = View.VISIBLE
            statusIV.setImageResource(R.drawable.ic_connection_error)
        }
        RestaurantesVM.ApiStatus.TERMINADO->{
            statusIV.visibility = View.GONE
        }
    }
}
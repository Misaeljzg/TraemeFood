package com.misaeljzg.traemefood.restaurantes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misaeljzg.traemefood.utils.ApiClient
import com.misaeljzg.traemefood.utils.ApiService
import com.misaeljzg.traemefood.utils.Restaurante
import kotlinx.coroutines.launch
import java.lang.Exception

class RestaurantesVM : ViewModel(){

    enum class ApiStatus {CARGANDO, ERROR, TERMINADO}

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status

    private val _restaurantes = MutableLiveData<List<Restaurante>>()
    val restaurantes : LiveData<List<Restaurante>> get() = _restaurantes

    private val _navigateToSelectedRestaurant = MutableLiveData<Restaurante>()
    val navigateToSelectedRestaurant: LiveData<Restaurante> get() = _navigateToSelectedRestaurant


    init {
        Log.i(" RestaurantesViewModel", "RestaurantesVM created")
        getRestaurants()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("RestaurantesViewModel", "RestaurantesVM destroyed")
    }

    fun displayRestaurantMenu(restaurante: Restaurante){
        _navigateToSelectedRestaurant.value = restaurante
    }

    fun displayRestaurantMenuComplete(){
        _navigateToSelectedRestaurant.value = null
    }

    private fun getRestaurants(){
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.CARGANDO
                val listResult = ApiClient.retrofitService.getRestaurantes()
                _status.value = ApiStatus.TERMINADO
                _restaurantes.value = listResult


            }catch (e: Exception){
                _status.value = ApiStatus.ERROR
                _restaurantes.value = ArrayList()
            }
        }
    }


}
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

    private val _status = MutableLiveData<String>()
    val response: LiveData<String> get() = _status

    private val _restaurante = MutableLiveData<Restaurante>()
    val restaurante : LiveData<Restaurante> get() = _restaurante


    init {
        Log.i(" RestaurantesViewModel", "RestaurantesVM created")
        getRestaurants()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("RestaurantesViewModel", "RestaurantesVM destroyed")
    }

    private fun getRestaurants(){
        viewModelScope.launch {
            try {
                var listResult = ApiClient.retrofitService.getRestaurantes()
                if(listResult.isNotEmpty()){
                    _restaurante.value = listResult[0]
                }
                //_status.value = "Success: ${listResult.size} Restaurantes encontrados!"
            }catch (e: Exception){
                _status.value = "Falla: ${e.message}"
            }
        }
    }


}
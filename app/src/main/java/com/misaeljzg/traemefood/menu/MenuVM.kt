package com.misaeljzg.traemefood.menu

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.misaeljzg.traemefood.utils.ApiClient
import com.misaeljzg.traemefood.utils.Platillo
import com.misaeljzg.traemefood.utils.Restaurante
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus {CARGANDO, ERROR, TERMINADO}

class MenuVM (restaurante: Restaurante, app: Application) : AndroidViewModel(app){
    private val _selectedRestaurant = MutableLiveData<Restaurante>()
    val selectedRestaurant : LiveData<Restaurante> get() = _selectedRestaurant

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status

    private val _menu = MutableLiveData<List<Platillo>>()
    val menu : LiveData<List<Platillo>> get() = _menu

    init{
        _selectedRestaurant.value = restaurante
        getMenu()
    }

    private fun getMenu() {
        viewModelScope.launch {
            _status.value = ApiStatus.CARGANDO
            try {
                _menu.value = ApiClient.retrofitService.getMenu(_selectedRestaurant.value?.idRes.toString())
                _status.value = ApiStatus.TERMINADO
                Log.d("JSON", menu?.value?.get(0)?.nombrePlatillo.toString())
            }catch (e: Exception){
                _status.value = ApiStatus.ERROR
                _menu.value = ArrayList()
            }
        }
    }
}
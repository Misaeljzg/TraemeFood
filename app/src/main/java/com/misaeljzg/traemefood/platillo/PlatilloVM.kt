package com.misaeljzg.traemefood.platillo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.misaeljzg.traemefood.roomdatabase.Orden
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO
import com.misaeljzg.traemefood.utils.Platillo
import kotlinx.coroutines.launch

class PlatilloVM(val database:OrdenDAO, platillo: Platillo, app: Application) : AndroidViewModel(app){
    private val _selectedPlatillo = MutableLiveData<Platillo>()
    val selectedPlatillo : LiveData<Platillo> get() = _selectedPlatillo

    private val _cantidadPlatillo = MutableLiveData<Int>()
    val cantidadPlatillo: MutableLiveData<Int> get() = _cantidadPlatillo

    private val _total = MutableLiveData <Float>()
    val total: MutableLiveData<Float> get() = _total

    private val platillo = platillo


    init {
        _selectedPlatillo.value = platillo
        _cantidadPlatillo.value = 0
        _total.value = 0f
    }

    fun addCantidad(precio: Long){
        _cantidadPlatillo.value = _cantidadPlatillo.value!!.toInt() + 1
        _total.value = _total.value!! + precio
    }

    fun menosCantidad(precio: Long){
        if(_cantidadPlatillo.value!! > 0){
            _cantidadPlatillo.value = _cantidadPlatillo.value!! - 1
            _total.value = _total.value!! - precio
        }
    }

    private suspend fun insert (orden: Orden){
        database.insert(orden)
    }

    fun onAddToOrder (){
        if(_cantidadPlatillo.value != 0) {
            viewModelScope.launch {
                val orden = Orden(platillo.idRestaurante, platillo.idPlatillo, platillo.nombrePlatillo, _cantidadPlatillo.value!!, _total.value!!)
                insert(orden)
            }
        }
    }
}
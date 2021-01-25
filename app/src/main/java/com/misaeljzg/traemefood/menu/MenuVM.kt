package com.misaeljzg.traemefood.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.misaeljzg.traemefood.utils.Restaurante

class MenuVM (restaurante: Restaurante, app: Application) : AndroidViewModel(app){
    private val _selectedRestaurant = MutableLiveData<Restaurante>()
    val selectedRestaurant : LiveData<Restaurante> get() = _selectedRestaurant

    init{
        _selectedRestaurant.value = restaurante
    }
}
package com.misaeljzg.traemefood.carrito

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO

class CarritoVMFactory (private val dataSource: OrdenDAO, private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarritoVM::class.java)){
            return CarritoVM(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown VM class")
    }

}
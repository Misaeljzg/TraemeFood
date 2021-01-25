package com.misaeljzg.traemefood.menu

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.utils.Restaurante
import java.lang.IllegalArgumentException

class MenuVMFactory (private val restaurante:Restaurante, private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuVM::class.java)){
            return MenuVM(
                restaurante,
                application
            ) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel class")
    }

}
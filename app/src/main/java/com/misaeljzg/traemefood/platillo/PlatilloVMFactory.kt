package com.misaeljzg.traemefood.platillo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO
import com.misaeljzg.traemefood.utils.Platillo
import java.lang.IllegalArgumentException

class PlatilloVMFactory  (private val dataSource: OrdenDAO, private val platillo: Platillo, private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlatilloVM::class.java)){
            return PlatilloVM(dataSource, platillo, application) as T
        }
        throw IllegalArgumentException ("Unknown ViewModelClass")
    }

}
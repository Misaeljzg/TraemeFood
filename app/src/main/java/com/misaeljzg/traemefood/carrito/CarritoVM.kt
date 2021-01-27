package com.misaeljzg.traemefood.carrito

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.misaeljzg.traemefood.roomdatabase.OrdenDAO

class CarritoVM (val database: OrdenDAO, application: Application) : AndroidViewModel (application){

}
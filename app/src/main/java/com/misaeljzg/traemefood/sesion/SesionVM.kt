package com.misaeljzg.traemefood.sesion

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import org.json.JSONException
import org.json.JSONObject

class SesionVM : ViewModel(){
    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus : LiveData<Boolean> get() = _loginStatus
    init {
        Log.i(" SesionViewModel", "SesionVM created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SesionViewModel", "SesionVM destroyed")
    }


    fun login(user: String, contra: String) {
        _loginStatus.value = true

    }



}
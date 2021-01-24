package com.misaeljzg.traemefood.restaurantes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.protobuf.Api
import com.misaeljzg.traemefood.utils.ApiClient
import com.misaeljzg.traemefood.utils.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RestaurantesVM : ViewModel(){

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    var mAPIService: ApiService? = null


    init {
        Log.i(" RestaurantesViewModel", "RestaurantesVM created")
        getRestaurants()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("RestaurantesViewModel", "RestaurantesVM destroyed")
    }

    private fun getRestaurants(){
        ApiClient.retrofitService.getRestaurantes().enqueue(object: retrofit2.Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure" + t.message
                Log.d("JSON", t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
                Log.d("JSON", response.body()!!)
            }
        })
    }


}
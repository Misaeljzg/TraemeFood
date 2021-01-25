package com.misaeljzg.traemefood.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://zgdev.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("restaurantes.php")
    suspend fun getRestaurantes() : List<Restaurante>

    @POST("menu.php")
    @FormUrlEncoded
    suspend fun getMenu(@Field("restaurante") idrestaurante: String) : List<Platillo>
}
object ApiClient {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
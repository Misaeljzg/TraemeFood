package com.misaeljzg.traemefood.utils

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://zgdev.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("restaurantes.php")
    fun getRestaurantes() : Call<String>
//    @POST("/posts")
//    @FormUrlEncoded
//    fun savePost(@Field("title") title: String?, @Field("body") body: String?, @Field("userId") userId: Long ): Call<Post?>?
}
object ApiClient {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
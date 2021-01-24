package com.misaeljzg.traemefood.utils

import com.squareup.moshi.Json

data class Restaurante(
    @Json(name = "ID_Restaurante") val idRes: String,
    @Json(name = "Nombre_Rest")  val nombreRes: String,
    @Json(name = "Descripcion") val descRes: String,
    @Json(name = "Calle") val calle: String,
    @Json(name = "Colonia") val colonia: String,
    @Json(name = "Imagen_Path") val imgPath: String
)
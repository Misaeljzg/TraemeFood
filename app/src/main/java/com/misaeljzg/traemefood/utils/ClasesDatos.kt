package com.misaeljzg.traemefood.utils

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurante(
    @Json(name = "ID_Restaurante") val idRes: String,
    @Json(name = "Nombre_Rest")  val nombreRes: String,
    @Json(name = "Descripcion") val descRes: String,
    @Json(name = "Calle") val calle: String,
    @Json(name = "Colonia") val colonia: String,
    @Json(name = "Imagen_Path") val imgPath: String,
    @Json(name = "Horario") val horario: String,
    @Json(name = "Precio_Inferior") val precioInferior: String,
    @Json(name = "Precio_Superior") val precioSuperior: String
) : Parcelable

@Parcelize
data class Platillo(
    @Json (name = "ID_Platillo") val idPlatillo: String,
    @Json (name = "Nombre_Platillo") val nombrePlatillo: String,
    @Json (name = "Ingredientes") val ingredientes: String,
    @Json (name = "Precio") val precioPlatillo: String,
    @Json (name = "Imagen") val imagenPlatillo: String
) : Parcelable
package com.misaeljzg.traemefood.utils

import com.beust.klaxon.Json
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurante  (
    @SerializedName("ID_Restaurante")
    @Expose
    private var iDRestaurante: String,

    @SerializedName("Nombre_Rest")
    @Expose
    private var nombreRest: String,

    @SerializedName("Descripcion")
    @Expose
    private var descripcion: String,

    @SerializedName("Calle")
    @Expose
    private var calle: String,

    @SerializedName("Colonia")
    @Expose
    private var colonia: String,

    @SerializedName("Imagen_Path")
    @Expose
    private var imagenPath: String
)
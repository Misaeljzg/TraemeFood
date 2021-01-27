package com.misaeljzg.traemefood.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.misaeljzg.traemefood.utils.Platillo

@Entity (tableName = "orden_tabla")
data class Orden(
    @PrimaryKey(autoGenerate = true)
    var idOrden: Long = 0L,
    @ColumnInfo(name = "id_Restaurante")
    var idRest: String = "",
    @ColumnInfo(name = "id_platillo")
    var idPlatillo: String = "",
    @ColumnInfo(name = "nombre_platillo")
    var nombrePlatillo: String = "",
    @ColumnInfo(name = "cantidad_platillo")
    var cantidadPlatillo: Int = 0,
    @ColumnInfo(name = "precio_total")
    var total: Float = 0f
)
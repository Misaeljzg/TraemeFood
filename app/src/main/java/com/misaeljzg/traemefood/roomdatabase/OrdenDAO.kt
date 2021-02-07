package com.misaeljzg.traemefood.roomdatabase

import androidx.room.*

@Dao
interface OrdenDAO {
    @Insert
    suspend fun insert(orden: Orden)

    @Update
    suspend fun update(orden: Orden)

    @Query("SELECT * from orden_tabla WHERE id_Restaurante = :key")
    suspend fun getOrdenes(key: String) : List<Orden>

    @Query("DELETE FROM orden_tabla WHERE id_Restaurante = :key")
    suspend fun clear(key: String)

    @Query ("DELETE FROM orden_tabla WHERE idOrden= :key")
    suspend fun delete (key: Int)

    @Delete
    suspend fun deleteOrden(orden: Orden)

    @Query("SELECT SUM(precio_total) as total FROM orden_tabla WHERE id_Restaurante = :key")
    suspend fun getTotal(key: String) : Float
}

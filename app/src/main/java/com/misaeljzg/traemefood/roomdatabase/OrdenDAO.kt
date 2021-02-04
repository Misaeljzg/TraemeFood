package com.misaeljzg.traemefood.roomdatabase

import androidx.room.*

@Dao
interface OrdenDAO {
    @Insert
    suspend fun insert(orden: Orden)

    @Update
    suspend fun update(orden: Orden)

    @Query("SELECT * from orden_tabla WHERE id_Restaurante = :key")
    suspend fun get(key: Int) : List<Orden?>

    @Query("DELETE FROM orden_tabla WHERE id_Restaurante = :key")
    suspend fun clear(key: String)
}

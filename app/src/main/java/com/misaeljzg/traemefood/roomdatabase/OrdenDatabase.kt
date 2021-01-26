package com.misaeljzg.traemefood.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Orden::class], version = 1, exportSchema = false)
abstract class OrdenDatabase : RoomDatabase(){

    abstract val ordenDAO: OrdenDAO

    companion object{
        @Volatile
        private var INSTANCE: OrdenDatabase? = null

        fun getInstance(context: Context) : OrdenDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        OrdenDatabase::class.java,
                        "orden_history_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}


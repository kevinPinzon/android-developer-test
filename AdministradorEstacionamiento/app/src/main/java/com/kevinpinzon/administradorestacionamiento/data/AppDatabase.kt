package com.kevinpinzon.administradorestacionamiento.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar
import com.kevinpinzon.administradorestacionamiento.domain.CarDAO
import com.kevinpinzon.administradorestacionamiento.domain.TypeCarDAO

@Database(entities = [Car::class, TypeCar::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDAO(): CarDAO
    abstract fun typeCarDAO(): TypeCarDAO


    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }
                return instance
            }
        }

    }

}
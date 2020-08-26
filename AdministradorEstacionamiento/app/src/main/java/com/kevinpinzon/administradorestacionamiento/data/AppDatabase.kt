package com.kevinpinzon.administradorestacionamiento.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.domain.CarDAO

@Database(entities = [Car::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDAO(): CarDAO


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
                        "tabla_cars"
                    ).build()
                }
                return instance
            }
        }

    }

}
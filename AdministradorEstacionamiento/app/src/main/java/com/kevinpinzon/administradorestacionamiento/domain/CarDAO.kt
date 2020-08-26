package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kevinpinzon.administradorestacionamiento.data.model.Car

@Dao
interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Update
    fun updateCar(car: Car) : Int

    @Delete
    fun deleteCar(car: Car) : Int

    @Query("DELETE FROM car")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM car")
    fun getAllCars():LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE type == 'oficial'")
    fun getOficialCars():LiveData<List<Car>>
}

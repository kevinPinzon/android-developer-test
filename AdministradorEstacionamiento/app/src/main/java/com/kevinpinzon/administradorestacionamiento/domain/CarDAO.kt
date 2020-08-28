package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kevinpinzon.administradorestacionamiento.data.model.Car

@Dao
interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Query("UPDATE car SET totalTime = (totalTime + :totalTime) WHERE placa = :placa;")
    suspend fun updateTotalTime(placa: String, totalTime: Long) : Int

    @Query("UPDATE car SET totalTime = 0 WHERE type = 'residente';")
    suspend fun startMonthToResident() : Int

    @Update
    suspend fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car) : Int

    @Query("DELETE FROM car")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM car")
    fun getAllCars():LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE placa == :placa LIMIT 1")
    fun getCarByPlaca(placa: String) : LiveData<Car>

    @Query("SELECT * FROM car WHERE type == 'oficial'")
    fun getOficialCars():LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE type == 'residente'")
    fun getOResidentCars():LiveData<List<Car>>
}

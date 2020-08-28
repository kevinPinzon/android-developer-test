package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.Car

class CarRepo(private val carDAO: CarDAO) {

    val allcars: LiveData<List<Car>> = carDAO.getAllCars()

    fun getCarByPlaca(placa: String): LiveData<Car> {
        return carDAO.getCarByPlaca(placa)
    }

    suspend fun insert(car: Car) {
        carDAO.insertCar(car)
    }

    suspend fun updateTotalTime(placa: String, totalTime: Long) {
        carDAO.updateTotalTime(placa, totalTime)
    }

    suspend fun startMonthToResident() {
        carDAO.startMonthToResident()
    }
}
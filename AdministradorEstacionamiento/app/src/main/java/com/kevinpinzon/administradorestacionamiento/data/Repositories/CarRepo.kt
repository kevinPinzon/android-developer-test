package com.kevinpinzon.administradorestacionamiento.data.Repositories

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.data.DAO.CarDAO

class CarRepo(private val carDAO: CarDAO) {

    val allcars: LiveData<List<Car>> = carDAO.getAllCars()

    val allOficialCars: LiveData<List<Car>> = carDAO.getOficialCars()

    val allResidentCars: LiveData<List<Car>> = carDAO.getOResidentCars()

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
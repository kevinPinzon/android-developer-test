package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.Car

class CarRepo(private val carDAO: CarDAO) {

    val allcars: LiveData<List<Car>> = carDAO.getAllCars()

    suspend fun insert(car: Car) {
        carDAO.insertCar(car)
    }
}
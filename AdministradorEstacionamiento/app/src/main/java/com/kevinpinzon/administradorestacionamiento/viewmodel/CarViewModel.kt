package com.kevinpinzon.administradorestacionamiento.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevinpinzon.administradorestacionamiento.data.AppDatabase
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.domain.CarRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarRepo

    val allCars: LiveData<List<Car>>
    val allOficialCars: LiveData<List<Car>>
    val allResidentCars: LiveData<List<Car>>

    init {
        val carsDao = AppDatabase.getDatabase(application).carDAO()
        repository = CarRepo(carsDao)
        allCars = repository.allcars
        allOficialCars = repository.allOficialCars
        allResidentCars = repository.allResidentCars
    }

    fun insert(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(car)
    }

    fun updateTotalTime(placa: String, totalTime: Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTotalTime(placa, totalTime)
    }

    fun startMonthToResident() = viewModelScope.launch(Dispatchers.IO) {
        repository.startMonthToResident()
    }

    fun getCarByPlaca(placa: String): LiveData<Car>  {
        return repository.getCarByPlaca(placa)
    }
}
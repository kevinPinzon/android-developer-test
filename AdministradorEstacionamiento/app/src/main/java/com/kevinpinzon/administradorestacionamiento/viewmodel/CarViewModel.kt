package com.kevinpinzon.administradorestacionamiento.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevinpinzon.administradorestacionamiento.data.AppDatabase
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.domain.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repo

    val allCars: LiveData<List<Car>>

    init {
        val carsDao = AppDatabase.getDatabase(application).carDAO()
        repository = Repo(carsDao)
        allCars = repository.allcars
    }

    fun insert(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(car)
    }
}
package com.kevinpinzon.administradorestacionamiento.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevinpinzon.administradorestacionamiento.data.AppDatabase
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar
import com.kevinpinzon.administradorestacionamiento.data.Repositories.TypeCarRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TypeCarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TypeCarRepo

    val alltypeCars: LiveData<List<TypeCar>>

    init {
        val typeCarsDao = AppDatabase.getDatabase(application).typeCarDAO()
        repository =
            TypeCarRepo(
                typeCarsDao
            )
        alltypeCars = repository.allcars
    }

    fun insert(typeCar: TypeCar) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(typeCar)
    }
}
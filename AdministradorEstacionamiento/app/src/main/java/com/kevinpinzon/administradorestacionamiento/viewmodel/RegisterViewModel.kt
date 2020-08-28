package com.kevinpinzon.administradorestacionamiento.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevinpinzon.administradorestacionamiento.data.AppDatabase
import com.kevinpinzon.administradorestacionamiento.data.model.Register
import com.kevinpinzon.administradorestacionamiento.domain.RegisterRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RegisterRepo

    val allRegisters: LiveData<List<Register>>

    val allPlacasIn: LiveData<List<Register>>

    init {
        val registersDao = AppDatabase.getDatabase(application).registerDAO()
        repository = RegisterRepo(registersDao)
        allRegisters = repository.allregister
        allPlacasIn = repository.allPlacasIn
    }

    fun registerIn(register: Register) = viewModelScope.launch(Dispatchers.IO) {
        repository.registerIn(register)
    }

    fun registerOut(register: Register) = viewModelScope.launch(Dispatchers.IO) {
        repository.registerOut(register)
    }

    fun startMonth() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllRegisters()
    }
}
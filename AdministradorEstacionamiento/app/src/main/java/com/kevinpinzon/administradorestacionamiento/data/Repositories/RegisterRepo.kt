package com.kevinpinzon.administradorestacionamiento.data.Repositories

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.Register
import com.kevinpinzon.administradorestacionamiento.data.DAO.RegisterDAO

class RegisterRepo(private val registerDAO: RegisterDAO) {

    val allregister: LiveData<List<Register>> = registerDAO.getAllRegister()

    suspend fun registerIn(register: Register) {
        registerDAO.registerIn(register)
    }

    suspend fun registerOut(register: Register) {
        registerDAO.updateRegister(register)
    }

    suspend fun deleteAllRegisters() {
        registerDAO.deleteAll()
    }

    val allPlacasIn: LiveData<List<Register>> = registerDAO.getAllPlacasIn()

}
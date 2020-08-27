package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.Register

class RegisterRepo(private val registerDAO: RegisterDAO) {

    val allregister: LiveData<List<Register>> = registerDAO.getAllRegister()

    suspend fun registerIn(register: Register) {
        registerDAO.registerIn(register)
    }

    val allPlacasIn: LiveData<List<Register>> = registerDAO.getAllPlacasIn()

}
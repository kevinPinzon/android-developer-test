package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar

class TypeCarRepo(private val typeCarDAO: TypeCarDAO) {

    val allcars: LiveData<List<TypeCar>> = typeCarDAO.getAllTypeCars()

    suspend fun insert(typeCar: TypeCar) {
        typeCarDAO.insertTypecar(typeCar)
    }
}
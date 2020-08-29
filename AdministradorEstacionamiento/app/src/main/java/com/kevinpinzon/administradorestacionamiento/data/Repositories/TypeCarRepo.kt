package com.kevinpinzon.administradorestacionamiento.data.Repositories

import androidx.lifecycle.LiveData
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar
import com.kevinpinzon.administradorestacionamiento.data.DAO.TypeCarDAO

class TypeCarRepo(private val typeCarDAO: TypeCarDAO) {

    val allcars: LiveData<List<TypeCar>> = typeCarDAO.getAllTypeCars()

    suspend fun insert(typeCar: TypeCar) {
        typeCarDAO.insertTypecar(typeCar)
    }
}
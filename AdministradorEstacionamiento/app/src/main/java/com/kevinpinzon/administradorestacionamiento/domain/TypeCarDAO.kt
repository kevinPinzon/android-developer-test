package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar

@Dao
interface TypeCarDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTypecar(type: TypeCar)

    @Query("SELECT * FROM TypeCar")
    fun getAllTypeCars(): LiveData<List<TypeCar>>

}

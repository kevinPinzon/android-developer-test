package com.kevinpinzon.administradorestacionamiento.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kevinpinzon.administradorestacionamiento.data.model.Register

@Dao
interface RegisterDAO {
/*
@Query("INSERT INTO register (placa, timeIn)" +
            " VALUES( :placa, :timeIn );")
    suspend fun registerIn(placa: String, timeIn: Long)
 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerIn(register: Register)

    @Update
    fun updateRegister(register: Register) : Int

    @Delete
    fun deleteRegister(register: Register) : Int

    @Query("DELETE FROM register")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM register")
    fun getAllRegister():LiveData<List<Register>>

    @Query("SELECT DISTINCT placa, id FROM register WHERE"+
        " timeOut = 'N'")
    fun getAllPlacasIn():LiveData<List<Register>>

}

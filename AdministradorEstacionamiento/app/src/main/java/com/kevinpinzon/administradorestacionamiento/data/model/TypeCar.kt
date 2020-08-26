package com.kevinpinzon.administradorestacionamiento.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "typeCar")
data class TypeCar (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String = ""

)
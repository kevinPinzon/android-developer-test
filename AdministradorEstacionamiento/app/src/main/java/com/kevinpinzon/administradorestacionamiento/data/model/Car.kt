package com.kevinpinzon.administradorestacionamiento.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "car")

data class Car (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "placa")
    var placa : String = "",

    @ColumnInfo(name = "type")
    var type : String = "",

    @ColumnInfo(name = "totalTime")
    var totalTime : Float = 0F

)
package com.kevinpinzon.administradorestacionamiento.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register")

data class Register (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "placa")
    var placa : String,

    @ColumnInfo(name = "timeIn")
    var timeIn : String?,

    @ColumnInfo(name = "timeOut")
    var timeOut : String?,

    @ColumnInfo(name = "totalToPay")
    var totalToPay : Float? = 0F

)
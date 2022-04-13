package com.example.roadsafifinal.data.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Driver_table")
data class Driver(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val driver_name: String,
    val carModel: String,
    val comments: String?,
    val ratings: Int,
    val driverPhoto: Bitmap,
    val hire: Boolean
    )
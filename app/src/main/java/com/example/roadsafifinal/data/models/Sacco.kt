package com.example.roadsafifinal.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Sacco")
data class Sacco(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val saccoName: String,
    val routes: String,
    val sacco_members: List<String>?,
    val sacco_image: String

)
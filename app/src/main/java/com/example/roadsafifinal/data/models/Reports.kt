package com.example.roadsafifinal.data.models

import android.graphics.Bitmap
import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reports_table")
data class Reports (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "r_id") val id: Int,
    @ColumnInfo(name = "r_descprition") val description: String,
    @ColumnInfo(name = "r_image") val image: Bitmap?,
    @ColumnInfo(name="shared") val shared: Boolean?,
    @ColumnInfo(name = "r_feedback") val feedback: String?,
    @ColumnInfo(name = "r_location") val incidentArea: String

)
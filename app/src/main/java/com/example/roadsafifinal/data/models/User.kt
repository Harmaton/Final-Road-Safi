package com.example.roadsafifinal.data.models

//data class User (val fullName: String, val phoneNumber: String, val carOwned: String, val saccoName: String)

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
//import kotlinx.coroutines.NonDisposableHandle.parent

@Entity (tableName = "user_table")
data class User(
    @PrimaryKey (autoGenerate = true)
   @ColumnInfo(name = "id") val id: String,
    val fullName: String,
    val phoneNumber: String,
    val carOwned: String?,
    val saccoName: String?
    //val image:Bitmap?
)

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = User::class,
    parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
        ),

))
data class Address(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "addressid") val address_id: Int,
                   @ColumnInfo(name = "user_id" ) val id:Int,
                   @ColumnInfo(name = "address" ) val address: String,
                   @ColumnInfo(name = "city" ) val city: String,
                   @ColumnInfo(name = "county" ) val county: String
)
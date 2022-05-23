package com.example.roadsafifinal.data.fbmodels

import android.net.Uri
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Driverfb(var name: String,
                    var driverimage: String,
                    var carOwned: String,
                    var phoneNumber: String, var rating: String?

) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "driverimage" to driverimage,
            "carOwned" to carOwned,
        "phoneNumber" to phoneNumber,
            "rating" to rating

        )
    }
}
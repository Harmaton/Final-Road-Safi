package com.example.roadsafifinal.data.fbmodels

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Userfb( var fullname: String ?= "", var phoneNumber: String ?= "", var carOwned: String ?= "", var saccoName: String ?= ""){


    @Exclude
    fun toMap(): Map<String, Any?>{
        return mapOf(
            "fullName" to fullname,
            "saccoName" to saccoName,
            "phoneNumber" to phoneNumber,
            "carOwned" to carOwned


        )
    }
}




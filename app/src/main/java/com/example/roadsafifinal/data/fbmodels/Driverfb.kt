package com.example.roadsafifinal.data.fbmodels

import android.net.Uri

data class Driverfb(var name: String,
                    var driverimage: String,
                    var carOwned: String,
                    var phoneNumber: String, var rating: String?
){

    constructor(): this(
        "","","","",""
    )
}
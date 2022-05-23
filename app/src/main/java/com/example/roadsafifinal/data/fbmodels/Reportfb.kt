package com.example.roadsafifinal.data.fbmodels

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.request.target.ViewTarget
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.io.File

@IgnoreExtraProperties
data class Reportfb(
    var description: String?, var location: String?,
    var imageurl: String?
){

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "description" to description,
            "location" to location,
            "imageurl" to imageurl

        )
    }
}


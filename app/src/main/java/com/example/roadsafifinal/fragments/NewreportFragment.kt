package com.example.roadsafifinal.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.roadsafifinal.R
import com.example.roadsafifinal.data.fbmodels.Reportfb
import com.example.roadsafifinal.databinding.FragmentNewreportBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class NewreportFragment : Fragment() {

    private val CAMERA_REQUEST_CODE=2
    private val PICK_IMAGE_REQUEST = 1


    //Firebase
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var report: Reportfb
    private lateinit var uid: String
    private lateinit var imageUri: Uri


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bind = FragmentNewreportBinding.inflate(layoutInflater)


        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()



        val requestCamera=registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(activity, "Access Granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Access Denied", Toast.LENGTH_LONG).show()

                //look at this functionalirty******
                return@registerForActivityResult
            }
        }

        bind.btnCapture.setOnClickListener {
            requestCamera.launch(android.Manifest.permission.CAMERA)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }catch (e: ActivityNotFoundException){
                Toast.makeText( activity,"Error"+ e.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        bind.btnGallery.setOnClickListener {
            pickPhoto()

        }
        bind.btnReport.setOnClickListener {
            val description=bind.descriptionEt.text.toString()
            val location = bind.locationEt.text.toString()
            val image=bind.imgReport.imageAlpha

            if (TextUtils.isEmpty(description)){
                bind.descriptionEt.error="Cannot be blank"
               return@setOnClickListener
            }
            if (TextUtils.isEmpty(location)){
                bind.locationEt.error="input location"
                return@setOnClickListener
            }
            database=FirebaseDatabase.getInstance().getReference("Reportsfb")
            val reportfb=Reportfb(description,location, imager = null)
            database.child(uid).setValue(reportfb).addOnSuccessListener {
                Toast.makeText(context, "Report Entered Successfully", Toast.LENGTH_SHORT).show()
            }

        }

        return bind.root


    }


    private fun pickPhoto() {
        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val img= view?.findViewById<ImageView>(R.id.img_report)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null ){

            imageUri = data.data!!
            img?.setImageURI(imageUri)
            Toast.makeText( activity,"Image selected Successfully", Toast.LENGTH_LONG).show()

        }
        else if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            val bmp: Bitmap= data.extras?.get("data") as Bitmap
            img?.setImageBitmap(bmp)
            Toast.makeText( activity,"Photo Captured Successfully", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText( activity,"Error Loading Image", Toast.LENGTH_LONG).show()
        }
    }


    private fun validateInput() {
        //validation for report input on submit")

    }




    private fun clearFields() {

    }


            }


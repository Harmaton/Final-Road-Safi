package com.example.roadsafifinal.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.roadsafifinal.R
import com.example.roadsafifinal.databinding.FragmentNewreportBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import java.util.jar.Manifest


class NewreportFragment : Fragment() {

    lateinit var imageUri: Uri


    private val CAMERA_REQUEST_CODE=2
    private val PICK_IMAGE_REQUEST = 1
    private val description=""
    private val location=""



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bind = FragmentNewreportBinding.inflate(layoutInflater)

        var mUploadTask: StorageTask<*>? = null
      var mStorageRef = FirebaseStorage.getInstance().getReference("teachers_uploads")
      var  databaseRef = FirebaseDatabase.getInstance().getReference("teachers_uploads")

        val requestCamera=registerForActivityResult(ActivityResultContracts.RequestPermission(), {
            if (it) {
                Toast.makeText( activity,"Access Granted", Toast.LENGTH_LONG).show()
            }else
            {
                Toast.makeText( activity,"Access Denied", Toast.LENGTH_LONG).show()
            }
        })

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
            validateInput()
            saveReport()
            clearFields()
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

    private fun saveReport() {
       // ("save report to Room Database")


    }



            }


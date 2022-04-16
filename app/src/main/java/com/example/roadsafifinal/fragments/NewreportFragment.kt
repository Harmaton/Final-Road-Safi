package com.example.roadsafifinal.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.roadsafifinal.databinding.FragmentNewreportBinding


@Suppress("UNREACHABLE_CODE")
class NewreportFragment : Fragment() {

    private var _binding: FragmentNewreportBinding? =null
    private val binding get() = _binding!!

    private val CAMERA_REQUEST_CODE=1
    private val description=""
    private val location=""
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewreportBinding.inflate(inflater, container, false)
        return binding.root


        //bind image widget view to img report
        imageView=binding.imgReport




        binding.btnCapture.setOnClickListener {
           cameracheckPermission()
            takePhoto(imageView)
        }

        binding.btnGallery.setOnClickListener {

        }

        binding.imgReport.setOnClickListener {
            describeImage()

        }
        binding.btnReport.setOnClickListener {
            validateInput()
            saveReport()
            clearFields()

        }

    }

    private fun validateInput() {
        //validation for report input on submit")
    }

    private fun takePhoto(view: View) {
        //capture the image and save it to img view
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //get a variable for result
        val getAction=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //get bitmap for image taken
            val bitmap = it?.data?.extras?.get("data") as Bitmap

            //set the bitmap to image view
            imageView.setImageBitmap(bitmap)


        }

        getAction.launch(intent)


    }

    private fun clearFields() {

    }

    private fun saveReport() {
       // ("save report to Room Database")
    }

    private fun describeImage() {
        //("tool tip on click")
    }

    private fun cameracheckPermission() {
        // Check if user grants permission for capture



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

            }


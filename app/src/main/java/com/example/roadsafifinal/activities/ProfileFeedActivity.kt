package com.example.roadsafifinal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.roadsafifinal.data.fbmodels.Userfb
import com.example.roadsafifinal.databinding.ActivityProfileFeedBinding
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class ProfileFeedActivity : AppCompatActivity() {
    //activity binding
    private lateinit var binding: ActivityProfileFeedBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val database= Firebase.database


        //addedd
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        database = Firebase.database.reference



        binding = ActivityProfileFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.contButton.setOnClickListener {
            submitProfile()

        }

    }

    private fun submitProfile() {

        val fullName = binding.textInputEditText.text.toString()
        val phoneNumber = binding.editText.text.toString()
        val carOwned = binding.editText2.text.toString()
        val sacco = binding.editText3.text.toString()

        // fullname is required
        if (TextUtils.isEmpty(fullName)) {
            binding.textInputEditText.error = "Required"
            return
        }

        // phone is required
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.editText.error = "REQUIRED"
            return
        }

        if (TextUtils.isEmpty(carOwned)) {
            binding.editText2.error = "REQUIRED"
            return
        }
        if (TextUtils.isEmpty(sacco)) {
            binding.editText3.error = "REQUIRED"
            return
        }



        database = FirebaseDatabase.getInstance().getReference("Userfb")
        val userfb = Userfb(fullName, phoneNumber, carOwned, sacco)
        database.child(uid).setValue(userfb).addOnSuccessListener {

            binding.textInputEditText.text?.clear()
            binding.editText.text?.clear()
            binding.editText2.text?.clear()
            binding.editText3.text?.clear()

            Toast.makeText(this, "User Information Recorded", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, ReportsActivity::class.java))
        }
            .addOnFailureListener {

                Toast.makeText(this, "User Information Not Recorded", Toast.LENGTH_SHORT).show()
            }



    }


}





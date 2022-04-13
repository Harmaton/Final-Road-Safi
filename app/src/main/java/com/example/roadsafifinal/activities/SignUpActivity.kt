package com.example.roadsafifinal.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.roadsafifinal.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivitySignUpBinding
    //action bar

    //progress dialog
    private lateinit var progressDialog: ProgressDialog

    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    private var email=""
    private var password=""
    private var password2=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //action bar
        //actionbar.setDisplayedHomeasEnabled(true)
        //actionbar.setDisplayedShowasEnabled(true)


        //configure progress dialog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("registering...")
        progressDialog.setCanceledOnTouchOutside(false)

       //override fun onSupportNavigateUp(): Boolean{
        //onBackPressed()
        //return super.onSupportNavigateUp
    // }

        //FIREBASE INSTANCE
        firebaseAuth= FirebaseAuth.getInstance()


        //handle click , -> begin register
        binding.signupbtn.setOnClickListener{
            //check password match
          //  passwordCheck()
            //validate Data
            validateData()
            firebaseSignUp()
        }

    }





    private fun validateData(){
       //get Data
        email=binding.emailtext.text.toString().trim()
        password=binding.txtpassword.text.toString().trim()
        password2=binding.txtpassword2.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailtext.error="Not a valid Email!"

        }
        else if(TextUtils.isEmpty(password)){
            binding.txtpassword.error="PassWord Cannot be empty"

        }
        else if(TextUtils.isEmpty(password)) {
            binding.txtpassword2.error = "PassWord match Cannot be empty"
        }
        else if(password.length<6 || password != password2){
            binding.txtpassword.error="Password must be greater than 6 characters and match"
        }

        else{
            //validate then continue
            firebaseSignUp()
        }

    }
    private fun firebaseSignUp(){
        //show progress
        progressDialog.show()

        //create Account
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //on success code ->
                  //get current user
                val firebaseUser=firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this, "Registered with email ${email}", Toast.LENGTH_LONG)
                //go to login
                startActivity(Intent(this, ProfileFeedActivity::class.java))
                finish()
            }
            .addOnFailureListener{
                //on failure code->
              progressDialog.dismiss()

                Toast.makeText(this, "Failure to Register, Try Again", Toast.LENGTH_LONG)
            }
    }

}
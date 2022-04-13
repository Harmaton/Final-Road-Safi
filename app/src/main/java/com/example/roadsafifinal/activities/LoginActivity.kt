package com.example.roadsafifinal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.os.Message
import android.text.TextUtils
import android.util.Patterns
//import android.widget.ProgressBar
//import android.widget.TextView
import android.widget.Toast
import com.example.roadsafifinal.databinding.ActivityLoginBinding
//import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

   //viewBinding
    private lateinit var binding: ActivityLoginBinding
    //Action Bar
   // private lateinit var actionBar: ActionBar
    //textviews
    //firebaseAnalytics
   // private lateinit var analytics: FirebaseAnalytics
    //Firebase auth
    private lateinit var firebaseAuth: FirebaseAuth

    private var email=""
    private var password=""
    //private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // actionBar=supportActionBar!!
       // actionBar.title="Login"

       // analytics = FirebaseAnalytics.analytics


        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()


       //handle click , begin register
        binding.txtRegisterbtn.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // handle click , begin login
        binding.loginbtn.setOnClickListener{
            //validate data before login
            validateData()
            //navigate to Report user

        }

    }

    private fun validateData() {
        //getdata
        email= binding.emailtext.text.toString()
        password=binding.passwordtext.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           binding.emailtext.error="Invalid Email"
        }
        else if(TextUtils.isEmpty(password)){
          //no password error
            binding.passwordtext.error="Provide a password!"
        }
        else{
            //validate login
            firebaseLogin()
            //navigate to
        }

    }

    private fun checkUser(){
    // if user is already logged in, go to page
    //getCurrent user
        val firebaseUser= firebaseAuth.currentUser
        if (firebaseUser!=null){
            startActivity(Intent(this, ReportsActivity::class.java))
            finish()
        }


    }

    private fun firebaseLogin(){
        //set progress bar visible

        //firebase
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //success
                //get user data
                val firebaseUser=firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this, "Logged in as ${email}", Toast.LENGTH_LONG ).show()

                //openreports
                startActivity(Intent(this, ReportsActivity::class.java))
                finish()
            }
            .addOnFailureListener{
                //failure
              Toast.makeText(this, "Sorry, Failed to Login", Toast.LENGTH_LONG ).show()

            }

    }
}
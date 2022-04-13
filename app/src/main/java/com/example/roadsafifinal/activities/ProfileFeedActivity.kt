package com.example.roadsafifinal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.provider.Settings
import android.widget.Toast
import com.example.roadsafifinal.data.db.UserDatabase
import com.example.roadsafifinal.data.models.User
import com.example.roadsafifinal.databinding.ActivityProfileFeedBinding
//import com.google.firebase.auth.UserInfo
//import com.google.firebase.database.DatabaseReference
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch

//import com.google.firebase.database.FirebaseDatabase


class ProfileFeedActivity : AppCompatActivity() {
    //activity binding
    private lateinit var binding: ActivityProfileFeedBinding
    //firebase real-time
  // private lateinit var database: DatabaseReference
   //

    //private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityProfileFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.contButton.setOnClickListener{
            saveProfile()

        }
        binding.btnskip.setOnClickListener {
            skipProfile()
        }
    }

    private fun skipProfile() {
        startActivity(Intent(this, ReportsActivity::class.java))
    }

    private fun saveProfile() {

        //get the data
        val fullName= binding.textInputEditText.text.toString().trim()
        val phoneNumber=binding.editText.text.toString().trim()
        val carOwned=binding.editText2.text.toString().trim()
        val saccoName=binding.editText3.text.toString().trim()

        //validate Input
        if(!fullName.isEmpty() && !phoneNumber.isEmpty()){
            val user=User(fullName,phoneNumber,carOwned,carOwned, saccoName)

            val userDao=UserDatabase.getDatabase(this)?.UserDao()

            userDao?.insertUser(user)


            Toast.makeText(this,"Profile Information Set Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ReportsActivity::class.java))
            finish()
        }
        else{

            Toast.makeText(this,"Profile Information Not Set", Toast.LENGTH_SHORT).show()
        }



            }


        }


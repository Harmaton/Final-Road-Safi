package com.example.roadsafifinal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.roadsafifinal.R
import com.example.roadsafifinal.data.models.User
import com.example.roadsafifinal.databinding.ActivityReportsBinding
import com.example.roadsafifinal.fragments.HirepageFragment
import com.example.roadsafifinal.fragments.HomeFragment
import com.example.roadsafifinal.fragments.NewreportFragment
import com.example.roadsafifinal.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.ktx.Firebase

class ReportsActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityReportsBinding
    //firebase
    private lateinit var firebaseAuth: FirebaseAuth

   // private lateinit var recyclerView: RecyclerView

    private var email=""

    //navigation components
    private lateinit var navigationView: BottomNavigationView

    private val hirepageFragment= HirepageFragment()
    private val newreportFragment= NewreportFragment()
    private val profileFragment= ProfileFragment()
    private val reportsFragment=HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //innit firebaseAuth
        firebaseAuth= FirebaseAuth.getInstance()
        //check if user is logged in
        checkUser()

        replaceFragment(reportsFragment)
        navigationView=binding.bottomNavigationView
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_menu -> replaceFragment(reportsFragment)
                R.id.report_menu -> replaceFragment(newreportFragment)
                R.id.hire_menu -> replaceFragment(hirepageFragment)
                R.id.profile_menu -> replaceFragment(profileFragment)
            }
            true
        }




    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }


    public fun logOut(){
        firebaseAuth.signOut()



    }
    private fun checkUser(){
         val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser != null){
            // populate the home screen

        }
        else{
            //user is null go to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


}
package com.example.roadsafifinal.fragments

//import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.roadsafifinal.activities.LoginActivity
import com.example.roadsafifinal.data.fbmodels.Userfb
import com.example.roadsafifinal.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

//import com.google.firebase.storage.StorageReference

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    //private lateinit var storageReference: StorageReference
   // private lateinit var dialog: Dialog
    private lateinit var user: Userfb
    private lateinit var uid: String

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize Database
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        databaseReference= Firebase.database.reference

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            getUser()
        // Initialize Views
            val btnlogout = binding.btnLogout
            btnlogout.setOnClickListener{ logout() }

    }

    private fun getUser() {

        //val userfb=Userfb(fullname = null, carOwned = null, phoneNumber = null, saccoName = null)
        if (uid.isNotEmpty()) {
             databaseReference.addValueEventListener(object : ValueEventListener {
                 override fun onDataChange(snapshot: DataSnapshot) {
               val user = snapshot.getValue<Userfb>()
               if (user == null) {
                   Log.e("", "User $uid is unexpectedly null")
                   Toast.makeText(context,
                       "Error: could not fetch user.",
                       Toast.LENGTH_SHORT).show()
              }else{

                  displayUser()


               }

             }
              override fun onCancelled(error: DatabaseError) {
             }

               })


        }
    }

    private fun displayUser(

    ) {

         val userListener= object : ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 val users = snapshot.getValue<Userfb>()
                 for(user in snapshot.children){
                     if (users != null) {
                         binding.fullnameTv.setText(users.fullname)
                     }
                     binding.sacc0Tv.text=users?.saccoName.toString()
                     binding.carownedTv.text=users?.carOwned.toString()
                     binding.phoneTv.text=users?.phoneNumber.toString()
                 }


             }

             override fun onCancelled(error: DatabaseError) {

             }

         }
        databaseReference.child("Userfb").child(uid).addValueEventListener(userListener)
    }

    private fun changedp() {

    }

    private fun logout() {
        Firebase.auth.signOut()
        Toast.makeText(context, "Signing out  ... Ciao " , Toast.LENGTH_LONG).show()
        startActivity(Intent(context,LoginActivity::class.java))
    }

    private fun showProgressbar(){

    }

    }

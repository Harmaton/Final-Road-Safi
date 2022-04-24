package com.example.roadsafifinal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadsafifinal.R
import com.example.roadsafifinal.adapters.ReportAdapter
import com.example.roadsafifinal.data.fbmodels.Reportfb
import com.example.roadsafifinal.databinding.FragmentHomeBinding
import com.example.roadsafifinal.databinding.FragmentProfileBinding
import com.example.roadsafifinal.databinding.ReportItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private var _bindingh: FragmentHomeBinding? = null
    private val binding get() = _bindingh!!
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var databaseReference: DatabaseReference
    private lateinit var repotrrecycler: RecyclerView
     private lateinit var adapter: ReportAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _bindingh = FragmentHomeBinding.inflate(inflater, container, false)

        val  reportlist= ArrayList<Reportfb>()
        val adapter= ReportAdapter(reportlist)


        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        //create db
        databaseReference= Firebase.database.reference

       val  repotrrecycler=binding.reportRecycler
        //initialize aRecycler view

        repotrrecycler.adapter=adapter

        repotrrecycler.setHasFixedSize(true)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val  repotrrecycler=binding.reportRecycler
        //set up layout manager
        repotrrecycler.layoutManager=LinearLayoutManager(context)

        //set up fb recycler adapter with quuery
        checkuser()
        val reportlist= ArrayList<Reportfb>()
        val adapter=ReportAdapter(reportlist)


        repotrrecycler.adapter=adapter
    }

    override fun onStart() {
        super.onStart()
        checkuser()

    }

    private fun checkuser() {
        if (uid.isEmpty()){
            databaseReference.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val report=snapshot.getValue<Reportfb>()
                    if (report==null){
                        Log.e("", "reports for $uid is unexpectedly null")
                        Toast.makeText(context,
                            "Error: could not fetch Report.",
                            Toast.LENGTH_SHORT).show()
                    }
                    else{
                        geReportsdata()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }

    private fun geReportsdata() {

        val reportListener= object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val reportlist= arrayListOf<Reportfb>()
                val reports=snapshot.getValue<Reportfb>()
                    for (report in snapshot.children) {
                        if (report != null) {
                            repotrrecycler.adapter=adapter
                            reportlist.add(reports!!)


                        }

                    }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
         databaseReference.child("Reportsfb").child("Reports").addValueEventListener(reportListener)
    }
}





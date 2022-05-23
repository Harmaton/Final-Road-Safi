package com.example.roadsafifinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadsafifinal.R
import com.example.roadsafifinal.adapters.ReportAdapter
import com.example.roadsafifinal.data.fbmodels.Reportfb
import com.example.roadsafifinal.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import okhttp3.internal.notify


class HomeFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var repotrrecycler: RecyclerView
    private lateinit var myadapter: ReportAdapter
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     val view=inflater.inflate(R.layout.fragment_home, container, false)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        //create db
        database = Firebase.database.reference

        repotrrecycler = view.findViewById(R.id.reportRecycler)
        repotrrecycler.setHasFixedSize(true)
        repotrrecycler.layoutManager=LinearLayoutManager(activity)

        val reportlist = ArrayList<Reportfb>()
        myadapter = ReportAdapter(reportlist)

        repotrrecycler.adapter = myadapter



        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val reportlist = ArrayList<Reportfb>()
        myadapter = ReportAdapter(reportlist)

        repotrrecycler.adapter = myadapter

        fetchdata()


        }

    private fun fetchdata() {
        var reportlist=ArrayList<Reportfb>()
        repotrrecycler.layoutManager = LinearLayoutManager(activity)
        val myref = database.child("Reportsfb")
        myref.database.reference.child("Reports/")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {


                    for (snap in snapshot.children) {

                        val reportfb = snap.getValue<Reportfb>()
                        if (reportfb != null) {

                             reportlist.add(reportfb)


                        }
                       val rec= view!!.findViewById<RecyclerView>(R.id.reportRecycler)
                        rec.layoutManager=LinearLayoutManager(activity)
                        rec.setHasFixedSize(true)
                        myadapter=ReportAdapter(reportlist)
                        rec.adapter=myadapter
                    }

                }


                override fun onCancelled(error: DatabaseError) {

                }


            })


    }
}





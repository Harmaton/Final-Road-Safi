package com.example.roadsafifinal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roadsafifinal.R
import com.example.roadsafifinal.data.fbmodels.Reportfb

class ReportAdapter(private val reportList: ArrayList<Reportfb>): RecyclerView.Adapter<ReportAdapter.myViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewholder {

        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.report_item, parent, false)
        return myViewholder(itemview)
    }

    override fun onBindViewHolder(holder: myViewholder, position: Int) {

        val reportfb = reportList[position]
        holder.description.text=reportfb.description
        holder.location.text=reportfb.location
        holder.imageurl= reportfb.imageurl!!

    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    class myViewholder(itemview: View) :RecyclerView.ViewHolder(itemview){

        val description: TextView= itemview.findViewById(R.id.description_tv)
        val location: TextView= itemview.findViewById(R.id.location)
        var imageurl:ImageView=itemview.findViewById((R.id.img_report)
        )

    }

}
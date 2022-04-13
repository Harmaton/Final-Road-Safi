package com.example.roadsafifinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roadsafifinal.data.models.Reports

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items=ArrayList<Reports>()

    fun setList(data: ArrayList<Reports>){
        this.items=data
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val inflater= LayoutInflater.from(parent.context).inflate(R.layout.report_item,parent,false)
      return myViewHolder(inflater)
   }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class myViewHolder(view:View) : RecyclerView.ViewHolder(view){
         

    }
    fun bind(data: Reports){

    }
}
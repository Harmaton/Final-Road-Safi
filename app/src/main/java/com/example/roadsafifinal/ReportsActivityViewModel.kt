package com.example.roadsafifinal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Update
import com.example.roadsafifinal.data.db.UserDatabase
import com.example.roadsafifinal.data.models.Reports


class ReportsActivityViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var allReports: MutableLiveData<List<Reports>>

    init {
    allReports= MutableLiveData()

    }


    fun getAllReportsObservers(): MutableLiveData<List<Reports>>{
      return allReports
    }



    fun getAllReports() {
       val reportsDao= UserDatabase.getDatabase((getApplication()))?.ReportsDao()
        val list= reportsDao?.getAllReports()
       allReports.postValue(list!!)
    }

    fun UpdateReport (report: Reports) {
        val reportsDao= UserDatabase.getDatabase((getApplication()))?.ReportsDao()
        val list= reportsDao?.updateReport(report)
        getAllReports()
    }

    fun deleteReport(report: Reports) {
        val reportsDao= UserDatabase.getDatabase((getApplication()))?.ReportsDao()
        val list= reportsDao?.deleteReport(report)
       getAllReports()
    }
    fun addReport(report: Reports) {
        val reportsDao= UserDatabase.getDatabase((getApplication()))?.ReportsDao()
        val list= reportsDao?.addReport(report)
        getAllReports()
    }
}



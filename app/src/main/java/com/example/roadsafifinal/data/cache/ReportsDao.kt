package com.example.roadsafifinal.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roadsafifinal.data.models.Reports

@Dao
interface ReportsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addReport(report: Reports )

    @Delete
    fun deleteReport(report: Reports)

    @Update
    fun updateReport(report: Reports)

    @Query("SELECT * FROM Reports_table  ")
    fun getAllReports(): List<Reports>
}

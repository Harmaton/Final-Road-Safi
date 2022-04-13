package com.example.roadsafifinal.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*

interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDriver(driver: DriverDao)

    @Delete
    fun removeDriver(driver: DriverDao)

    @Update
    fun editDriver(driver: DriverDao)

    @Query("SELECT * from Driver_table ORDER BY id DESC")
    fun getAllDrivers(): LiveData<List<DriverDao>>
}

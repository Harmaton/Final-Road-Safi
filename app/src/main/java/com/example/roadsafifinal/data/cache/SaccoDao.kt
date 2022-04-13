package com.example.roadsafifinal.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.roadsafifinal.data.models.Sacco

@Dao
interface SaccoDao {
  @Insert
  suspend fun addSacco(saccos: Sacco)

  @Delete
  suspend fun deleteSacco(saccos: Sacco)



}
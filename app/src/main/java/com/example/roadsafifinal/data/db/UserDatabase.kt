package com.example.roadsafifinal.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roadsafifinal.data.cache.DriverDao
import com.example.roadsafifinal.data.cache.ReportsDao
import com.example.roadsafifinal.data.cache.SaccoDao
import com.example.roadsafifinal.data.cache.UserDao
import com.example.roadsafifinal.data.models.Driver
import com.example.roadsafifinal.data.models.Reports
import com.example.roadsafifinal.data.models.Sacco
import com.example.roadsafifinal.data.models.User

@Database(entities = [User::class, Sacco::class, Reports::class, Driver::class], version = 1, exportSchema = false)

abstract class UserDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao?
    abstract fun SaccoDao(): SaccoDao
    abstract fun ReportsDao(): ReportsDao
    abstract fun DriverDao():DriverDao

    companion object{

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase? {
            val instance= INSTANCE
            if (instance == null){
                return instance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().build()
                INSTANCE=instance
                return instance
            }
        }

    }
}

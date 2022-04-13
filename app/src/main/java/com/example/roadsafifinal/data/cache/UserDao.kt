package com.example.roadsafifinal.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roadsafifinal.data.models.Address
import com.example.roadsafifinal.data.models.User
import com.example.roadsafifinal.data.models.UserWithAddress


@Dao

interface UserDao {
    @Insert (onConflict = OnConflictStrategy.FAIL)
    fun insertUser(user: User): Long

    @Query ("SELECT * from user_table ORDER BY id DESC")
    fun getUserData(): List<User>

    @Insert
    fun insertAdrress(user: Address)

    @Query("SELECT * FROM user_table inner join Address on user_table.id = Address.user_id")
    fun getAllUserWithAddress( ): List<UserWithAddress>

}

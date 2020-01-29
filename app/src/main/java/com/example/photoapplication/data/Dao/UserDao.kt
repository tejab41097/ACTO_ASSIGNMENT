package com.example.photoapplication.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoapplication.data.user.User
import retrofit2.Response

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserToDb(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUsersToDb(users: List<User>)

    @Query("SELECT * FROM user")
    fun getAllUsersFromDb(): List<User>
}
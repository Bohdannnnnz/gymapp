package com.hm.gymapp.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hm.gymapp.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User?
}
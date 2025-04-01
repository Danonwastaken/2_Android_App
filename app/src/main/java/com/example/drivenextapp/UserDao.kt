package com.example.drivenextapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun insertItem(item: UserDbEntity)
    @Query("SELECT * FROM users")
    fun getAllItems(): Flow<List<UserDbEntity>>
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun checkUserExists(email: String, password: String) : UserDbEntity?

}
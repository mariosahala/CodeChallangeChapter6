package com.example.batuguntingkertas.data.database

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAllUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM UserEntity WHERE username = :username AND password=:password")
    fun getUser(username: String,password : String): UserEntity

    @Query("SELECT * FROM UserEntity")
    fun getUserValue(): UserEntity

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserId(id: Int?): UserEntity

    @Update
    fun updateUser(user: UserEntity): Int

    @Delete
    fun deleteUser(user: UserEntity): Int
}
package com.example.batuguntingkertas.data.database

import androidx.room.*

@Dao
interface FriendsDao {

    @Query("SELECT * FROM FriendsEntity ORDER BY idFriend ASC")
    fun getAllFriends(): MutableList<FriendsEntity>

    @Query("SELECT * FROM FriendsEntity WHERE idFriend=:id ")
    fun getFriend(id:Int): MutableList<FriendsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(user: FriendsEntity): Long

    @Update
    fun updateFriend(user: FriendsEntity): Int

    @Delete
    fun deleteFriend(user: FriendsEntity): Int



}
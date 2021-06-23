package com.example.batuguntingkertas.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FriendsEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @PrimaryKey(autoGenerate = true) var idFriend: Int
)
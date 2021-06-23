package com.example.batuguntingkertas.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class UserEntity(
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "image") var image: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
):Parcelable
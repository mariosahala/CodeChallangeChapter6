package com.example.batuguntingkertas.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, FriendsEntity::class], version = 1, exportSchema = false)
abstract class BigDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun friendsDao(): FriendsDao

    companion object {
        @Volatile
        private var INSTANCE: BigDatabase? = null

        fun getInstance(context: Context): BigDatabase? {
            if (INSTANCE == null) {
                synchronized(BigDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BigDatabase::class.java, "BigData.db").build()
                }
            }
            return INSTANCE
        }

    }
}
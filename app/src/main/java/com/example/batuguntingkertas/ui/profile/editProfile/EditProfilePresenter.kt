package com.example.batuguntingkertas.ui.profile.editProfile

import android.content.Context
import com.example.batuguntingkertas.data.database.BigDatabase
import com.example.batuguntingkertas.data.database.UserDao
import com.example.batuguntingkertas.data.database.UserEntity
import com.example.batuguntingkertas.data.lokal.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditProfilePresenter(context: Context, private val edit: EditProfileNavigator) {
    private var userDao: UserDao? = null
    val pref = SharedPref(context as EditProfileActivity)

    init {
        val userDb = BigDatabase.getInstance(context)
        userDao = userDb?.userDao()
    }

    fun editProfile(
        username: String,
        email: String,
        password: String,
        name: String,
        image: String
    ) {
        GlobalScope.launch {
            val ganti =
                userDao?.updateUser(UserEntity(username, email, password, image, id = 0))
            GlobalScope.launch(Dispatchers.Main) {
                if (ganti != 0) {
                    edit.editBerhasil("Edit profile berhasil")
                } else {
                    edit.editGagal("Edit profile gagal")
                }
            }
        }
    }

    fun ambilNama() {
        GlobalScope.launch(Dispatchers.IO) {
           //val nama = userDao?.getUser("nama")?.name

        }
    }

    fun ambilEmail() {
        GlobalScope.launch(Dispatchers.IO) {
           // val email = userDao?.getUser("email")?.email
        }
    }

    fun editSharedpref(username: String) {
        pref.username = username
    }

}








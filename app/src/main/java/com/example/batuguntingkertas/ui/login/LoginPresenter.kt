package com.example.batuguntingkertas.ui.login

import android.content.Context
import com.example.batuguntingkertas.data.database.BigDatabase
import com.example.batuguntingkertas.data.database.UserDao
import com.example.batuguntingkertas.data.lokal.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter (context: Context,private val navigator: LoginNavigator) {
    private var userDao :UserDao? = null
    val pref = SharedPref(context)
    init {
        val dB = BigDatabase.getInstance(context)
        userDao = dB?.userDao()
    }
    fun login (name : String, password : String){
        GlobalScope.launch {
            val id = userDao?.getUser(name,password)
            GlobalScope.launch (Dispatchers.Main){
                if (id==null){
                    navigator.errorLogin()
                }else {
                    navigator.succesLogin()
                    pref.id= id.id
                    pref.username = name
                    pref.isLogin = true
                }
            }
        }
    }
}
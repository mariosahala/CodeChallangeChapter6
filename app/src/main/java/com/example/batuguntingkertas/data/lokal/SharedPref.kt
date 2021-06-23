package com.example.batuguntingkertas.data.lokal

import android.content.Context

class SharedPref(context: Context) {
    private val pref = context.getSharedPreferences("myData", Context.MODE_PRIVATE)

    private val LOGIN = "LOGIN"
    private val USERNAME = "USERNAME"

    var id: Int?
        get() = pref?.getInt("ID", 0)
        set(value) {
            value?.let {
                pref?.edit()
                    ?.putInt("ID", it)
                    ?.apply()
            }
        }

    var username: String?
        get() = pref?.getString(USERNAME, "")
        set(value) {
            value.let {
                pref?.edit()
                    ?.putString(USERNAME, it)
                    ?.apply()
            }
        }

    var isLogin: Boolean?
        get() = pref?.getBoolean(LOGIN, false)
        set(value) {
            value?.let {
                pref?.edit()
                    ?.putBoolean(LOGIN, it)
                    ?.apply()
            }
        }

}
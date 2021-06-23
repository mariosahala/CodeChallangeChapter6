package com.example.batuguntingkertas.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.data.database.BigDatabase
import com.example.batuguntingkertas.data.lokal.SharedPref
import com.example.batuguntingkertas.ui.login.LoginActivity
import com.example.batuguntingkertas.ui.menu.MenuActivity
import com.example.batuguntingkertas.ui.profile.editProfile.EditProfileActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val fabBack = findViewById<FloatingActionButton>(R.id.fabBack)
        val name = findViewById<TextView>(R.id.tvName)
        val pref = SharedPref(this)

        GlobalScope.launch(Dispatchers.IO) {
            val getName =
                BigDatabase.getInstance(this@ProfileActivity)?.userDao()?.getUserId(pref.id)?.username
            name.text = getName
        }

        btnEdit.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            pref.isLogin = false
            startActivity(
                Intent(this, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            finish()
        }

        fabBack.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}
package com.example.batuguntingkertas.ui.menu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.ui.chatBotActivity.ChatBotActivity
import com.example.batuguntingkertas.ui.friends.FriendsActivity
import com.example.batuguntingkertas.ui.profile.ProfileActivity
import com.example.batuguntingkertas.ui.play.VsCpu
import com.example.batuguntingkertas.ui.play.VsPlayer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_menu.*
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val play = findViewById<Button>(R.id.btnPlay)
        val multiPlayer = findViewById<Button>(R.id.btnMulti)
        val friends = findViewById<Button>(R.id.btnFriends)
        val profile = findViewById<Button>(R.id.btnProfile)
        val exit = findViewById<Button>(R.id.btnExit)
        val chat = findViewById<FloatingActionButton>(R.id.fabChat)

        play.setOnClickListener { startActivity(Intent(this, VsCpu::class.java)) }
        multiPlayer.setOnClickListener { startActivity(Intent(this, VsPlayer::class.java)) }
        friends.setOnClickListener { startActivity(Intent(this, FriendsActivity::class.java)) }
        btnProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
        exit.setOnClickListener {
            moveTaskToBack(true)
            exitProcess(-1)
        }
        chat.setOnClickListener { startActivity(Intent(this, ChatBotActivity::class.java)) }

    }
}
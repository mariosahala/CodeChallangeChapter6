@file:Suppress("PrivatePropertyName", "PrivatePropertyName")

package com.asim.onboardingscreen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.batuguntingkertas.R

class IntroActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        mediaPlayer = MediaPlayer.create(this, R.raw.intro)
        mediaPlayer.start()
        val d = Log.d("SPLASH SCREEN", "SPLASH SCREEN")
        mediaPlayer.setOnCompletionListener {
            Intent(this, OnBoardActivity::class.java)
            Handler().postDelayed({
                startActivity(Intent(this, OnBoardActivity::class.java))
                finish()
                mediaPlayer.stop()
            }, SPLASH_TIME)
        }
    }
}

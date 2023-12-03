package com.example.keries.activity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.keries.R

class splashscreen : AppCompatActivity() {
    private val SPLASH_DELAY = 2000L // 2 seconds
    private var mediaPlayer: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
            // Using a Handler to delay the transition to the main activity

        // Initialize the MediaPlayer with the audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.music)

            Handler().postDelayed({
                val mainIntent = Intent(this,BaseHome::class.java)
                startActivity(mainIntent)
                finish()
            }, SPLASH_DELAY)
        }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
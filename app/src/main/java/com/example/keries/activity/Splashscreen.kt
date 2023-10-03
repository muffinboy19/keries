package com.example.keries.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.keries.R

class splashscreen : AppCompatActivity() {
    private val SPLASH_DELAY = 4000L // 2 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
            // Using a Handler to delay the transition to the main activity
            Handler().postDelayed({
                val mainIntent = Intent(this,BaseHome::class.java)
                startActivity(mainIntent)
                finish()
            }, SPLASH_DELAY)
        }
}
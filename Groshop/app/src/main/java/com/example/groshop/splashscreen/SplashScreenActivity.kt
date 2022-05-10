package com.example.groshop.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.groshop.MainActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.groshop.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1800)
    }
}
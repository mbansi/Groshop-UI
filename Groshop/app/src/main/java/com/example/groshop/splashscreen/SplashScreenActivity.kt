package com.example.groshop.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.groshop.R
import com.example.groshop.authentication.SignInActivity
import com.example.groshop.onboarding.OnBoardingActivity

class SplashScreenActivity: AppCompatActivity() {

    private lateinit var delayHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed( {
            val intent = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}









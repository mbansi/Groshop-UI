package com.example.groshop.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.groshop.R
import com.example.groshop.onboarding.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var delay: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        displaySplashScreen()
    }

    private fun displaySplashScreen() {
        delay = Handler(Looper.getMainLooper())
        delay.postDelayed({
            val intent = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, 1800)
    }

    override fun onPause() {
        super.onPause()
        delay.removeCallbacksAndMessages(null)
    }

    override fun onResume() {
        super.onResume()
        displaySplashScreen()
    }
}
package com.example.groshop.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.groshop.R
import com.example.groshop.onboarding.OnBoardingActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var delay: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        AppCenter.start(
            application, "d8b84a49-bef9-4c6b-a327-e53c0c61323c",
            Analytics::class.java, Crashes::class.java
        )
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
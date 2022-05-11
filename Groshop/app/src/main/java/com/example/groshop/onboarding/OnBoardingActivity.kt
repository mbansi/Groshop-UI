package com.example.groshop.onboarding

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.groshop.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator
import android.content.Intent
import com.example.groshop.MainActivity
import com.example.groshop.R

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private var prevStarted = "prevStarted"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        binding.vpOnBoarding.adapter = OnBoardingAdapter(OnBoardingModel.getOnBoardData())

        TabLayoutMediator(binding.tab, binding.vpOnBoarding) { _, _ -> }.attach()
        setSpannableText()
        val sharedpreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, true)
            editor.apply()
        } else {
            val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.tvAlready.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvAlready.text = spannable
        binding.tvAlready.movementMethod = LinkMovementMethod.getInstance()
    }
}
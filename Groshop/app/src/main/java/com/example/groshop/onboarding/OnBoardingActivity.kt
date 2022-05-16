package com.example.groshop.onboarding

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.groshop.authentication.activity.SignUpActivity

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.example.groshop.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

import com.example.groshop.R
import com.example.groshop.authentication.activity.SignInActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private var prevStarted = "prevStarted"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        if (isFirstTime()) {
            binding.vpOnBoarding.adapter = OnBoardingAdapter(OnBoardingModel.getOnBoardData())
            TabLayoutMediator(binding.tab, binding.vpOnBoarding) { _, _ -> }.attach()
            setSpannableText()
            onClick()
        }
    }

    private fun isFirstTime() : Boolean{
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        return if (sharedPreferences.getBoolean(prevStarted, true)) {
            val editor = sharedPreferences.edit()
            editor.putBoolean(prevStarted, false)
            editor.apply()
            true
        } else {
            val intent = Intent(this@OnBoardingActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
            false
        }
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.tvAlready.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {

                val signInIntent = Intent(this@OnBoardingActivity, SignInActivity::class.java)
                signInIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(signInIntent)
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvAlready.text = spannable
        binding.tvAlready.movementMethod = LinkMovementMethod.getInstance()

        onClick()
    }

    private fun onClick() {
        binding.btnSignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            signUpIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(signUpIntent)
            finish()
        }
    }
}
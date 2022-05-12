package com.example.groshop.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.groshop.authentication.SignUpActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.example.groshop.R
import com.example.groshop.authentication.SignInActivity
import com.example.groshop.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        binding.vpOnBoarding.adapter = OnBoardingAdapter(OnBoardingModel.getOnBoardData())
        TabLayoutMediator(binding.tab, binding.vpOnBoarding) { tab, position -> }.attach()
        setSpannableText()
        onClick()
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.tvAlready.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@OnBoardingActivity,SignInActivity::class.java)
                signInIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(signInIntent)
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

    private fun onClick() {
        binding.btnSignUp.setOnClickListener {
            val signUpIntent = Intent(this,SignUpActivity::class.java)
            signUpIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(signUpIntent)
        }
    }
}
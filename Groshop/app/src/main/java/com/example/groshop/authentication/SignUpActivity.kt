package com.example.groshop.authentication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.groshop.R
import com.example.groshop.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        setSpannableText()
        onClick()
    }

    private fun setSpannableText() {
        val spanAlreadyExisting = SpannableString(binding.tvAlreadyExisting.text)
        val clickAlreadyExisting: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
                signInIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(signInIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spanAlreadyExisting.setSpan(clickAlreadyExisting, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvAlreadyExisting.text = spanAlreadyExisting
        binding.tvAlreadyExisting.movementMethod = LinkMovementMethod.getInstance()

        val spannable = SpannableString(binding.cbPrivacy.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(applicationContext, "Privacy policy", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, 29, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.cbPrivacy.text = spannable
        binding.cbPrivacy.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun onClick() {
        binding.btnSignUp.setOnClickListener {
            Toast.makeText(applicationContext, "Sign Up", Toast.LENGTH_SHORT).show()
        }
        binding.imgBtnFacebook.setOnClickListener {
            Toast.makeText(applicationContext,"Facebook",Toast.LENGTH_SHORT).show()
        }
        binding.imgBtnApple.setOnClickListener {
            Toast.makeText(applicationContext,"Apple",Toast.LENGTH_SHORT).show()
        }
        binding.imgBtnGoogle.setOnClickListener {
            Toast.makeText(applicationContext,"Google",Toast.LENGTH_SHORT).show()
        }
    }
}
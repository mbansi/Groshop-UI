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
        val spannable = SpannableString(binding.tvAlreadyExisting.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(signInIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvAlreadyExisting.text = spannable
        binding.tvAlreadyExisting.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun onClick() {
        binding.btnSignUp.setOnClickListener {
            Toast.makeText(applicationContext, "Sign Up", Toast.LENGTH_SHORT).show()
        }
    }
}
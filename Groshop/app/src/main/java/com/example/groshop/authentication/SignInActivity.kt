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
import com.example.groshop.databinding.ActivitySignInBinding

class SignInActivity: AppCompatActivity() {

    private  lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        setSpannableText()
        onClick()
    }

    private fun onClick() {
        binding.btnSignIn.setOnClickListener {
            Toast.makeText(applicationContext,"Signed In",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.tvNoAccount.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signUpIntent = Intent(this@SignInActivity,SignUpActivity::class.java)
                startActivity(signUpIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvNoAccount.text = spannable
        binding.tvNoAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}
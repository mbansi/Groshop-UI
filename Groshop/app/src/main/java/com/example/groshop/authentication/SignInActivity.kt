package com.example.groshop.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.groshop.R
import com.example.groshop.databinding.ActivitySignInBinding

class SignInActivity: AppCompatActivity() {

    private  lateinit var binding: ActivitySignInBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        setSpannableText()
        onClick()
        closeKeyBoard()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun onClick() {
        binding.btnSignIn.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(applicationContext,"Signed In",Toast.LENGTH_SHORT).show()
            }
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

    private fun setSpannableText() {
        val spannable = SpannableString(binding.tvNoAccount.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signUpIntent = Intent(this@SignInActivity,SignUpActivity::class.java)
                signUpIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

    private fun validateForm(): Boolean {
        if (getValues(binding.etEmail).isEmpty()) {
            Toast.makeText(applicationContext,"Enter email",Toast.LENGTH_SHORT).show()
            return false
        }
        if (!getValues(binding.etEmail).matches(emailPattern.toRegex())) {
            Toast.makeText(applicationContext,"Invalid Email",Toast.LENGTH_SHORT).show()
            return false
        }
        if (getValues(binding.etPassword).isEmpty()) {
            Toast.makeText(applicationContext,"Enter password",Toast.LENGTH_SHORT).show()
            return false
        }
        if (getValues(binding.etPassword).length < 8) {
            Toast.makeText(applicationContext,"Password too short",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun getValues(name: EditText): String {
        return name.text.toString()
    }
}
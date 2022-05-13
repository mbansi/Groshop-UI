package com.example.groshop.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.groshop.R
import com.example.groshop.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        setSpannableText()
        onClick()
        textChange()
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
                Toast.makeText(applicationContext, "Signed In", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Cannot sign in", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnForgotPassword.setOnClickListener {
            val forgotPasswordIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(forgotPasswordIntent)
        }
        binding.imgBtnFacebook.setOnClickListener {
            Toast.makeText(applicationContext, "Facebook", Toast.LENGTH_SHORT).show()
        }
        binding.imgBtnApple.setOnClickListener {
            Toast.makeText(applicationContext, "Apple", Toast.LENGTH_SHORT).show()
        }
        binding.imgBtnGoogle.setOnClickListener {
            Toast.makeText(applicationContext, "Google", Toast.LENGTH_SHORT).show()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.tvNoAccount.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signUpIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
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

    private fun textChange() {
        binding.etEmail.doOnTextChanged { text, _, _, _ ->
            when (true) {
                text.toString().isEmpty() -> binding.emailInputLayout.error = "Enter email"
                !android.util.Patterns.EMAIL_ADDRESS.matcher(text.toString())
                    .matches() -> binding.emailInputLayout.error = "Invalid Email"
                else -> {
                    binding.emailInputLayout.error = null
                }
            }
        }
        binding.etPassword.doOnTextChanged { text, _, _, _ ->
            when (true) {
                text.toString().isEmpty() -> binding.passwordInputLayout.error = "Enter password"
                text.toString().length < 8 -> binding.passwordInputLayout.error =
                    "Password too short"
                else -> {
                    binding.passwordInputLayout.error = null
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        when (true) {
            getValues(binding.etEmail).isEmpty() -> binding.emailInputLayout.error = "Enter email"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                .matches() -> binding.emailInputLayout.error = "Invalid Email"
            getValues(binding.etPassword).isEmpty() -> binding.passwordInputLayout.error =
                "Enter password"
            getValues(binding.etPassword).length < 8 -> {
                binding.passwordInputLayout.error = "Password too short"
                return false
            }
            else -> return true
        }
        return false
    }

    private fun getValues(name: EditText): String {
        return name.text.toString()
    }
}
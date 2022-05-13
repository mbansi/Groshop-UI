package com.example.groshop.authentication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.groshop.R
import com.example.groshop.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.toolbar.customToolbar) {
            this.navigationIcon = AppCompatResources.getDrawable(applicationContext,R.drawable.back_arrow)
            this.setNavigationOnClickListener {

                onBackPressed()
            }
        }
        setSpannableText()
        textChange()
        onClick()
    }


    private fun setSpannableText() {
        val spanAlreadyExisting = SpannableString(binding.tvAlreadyExisting.text)
        val clickAlreadyExisting: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
                signInIntent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(signInIntent)
                finish()
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

    private fun textChange() {
        binding.etFullName.doOnTextChanged { text, _, _, _ ->
            when(true) {
                text.toString().isEmpty() -> binding.fullNameInputLayout.error = "Full Name Required"
                else -> binding.fullNameInputLayout.error = null
            }
        }
        binding.etEmail.doOnTextChanged { text, _,_ ,_  ->
            when(true) {
                text.toString().isEmpty() -> binding.emailInputLayout.error = "Email Required"
                !android.util.Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches() -> binding.emailInputLayout.error = "Invalid Email"
                else -> { binding.emailInputLayout.error = null }
            }
        }
        binding.etPassword.doOnTextChanged { text, _, _, _ ->
            when(true) {
                text.toString().isEmpty() -> binding.passwordInputLayout.error = "Enter password"
                text.toString().length < 8 -> binding.passwordInputLayout.error = "Password too short"
                else -> {binding.passwordInputLayout.error = null }
            }
        }
        binding.etConfirmPassword.doOnTextChanged { text, _, _, _ ->
            when(true) {
                text.toString().isEmpty() -> binding.confirmPasswordInputLayout.error = "Enter password"
                text.toString() != getValues(binding.etPassword) -> binding.confirmPasswordInputLayout.error = "Passwords don't match"
                else -> { binding.confirmPasswordInputLayout.error = null }
            }
        }
    }

    private fun onClick() {
        binding.btnSignUp.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(applicationContext, "Signed Up", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Cannot sign up", Toast.LENGTH_SHORT).show()
            }
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

    private fun validateForm(): Boolean {
        when (true) {
            getValues(binding.etFullName).isEmpty() -> binding.fullNameInputLayout.error = "Enter full name"
            getValues(binding.etEmail).isEmpty() -> binding.emailInputLayout.error = "Enter email"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches() -> binding.emailInputLayout.error = "Invalid Email"
            getValues(binding.etPassword).isEmpty() -> binding.passwordInputLayout.error = "Enter password"
            getValues(binding.etConfirmPassword).isEmpty() -> binding.confirmPasswordInputLayout.error = "Enter password again"
            getValues(binding.etPassword).length < 8 -> binding.passwordInputLayout.error = "Password too short"
            getValues(binding.etConfirmPassword) != getValues(binding.etPassword) -> {
                binding.confirmPasswordInputLayout.error = "Passwords don't match"
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
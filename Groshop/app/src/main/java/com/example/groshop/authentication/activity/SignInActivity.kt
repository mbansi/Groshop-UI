package com.example.groshop.authentication.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.groshop.BaseAcitivity
import com.example.groshop.R
import com.example.groshop.databinding.ActivitySignInBinding
import com.example.groshop.home.activity.DashboardActivity

class SignInActivity : BaseAcitivity() {
import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.UserLoginSuccessModel
import com.example.groshop.databinding.ActivitySignInBinding
import com.example.groshop.home.activity.DashboardActivity
import com.example.groshop.utils.makeApiCall
import org.json.JSONObject
import java.net.URL

class SignInActivity : BaseAcitivity(), ApiResponse {

    private lateinit var binding: ActivitySignInBinding

    @RequiresApi(Build.VERSION_CODES.R)
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
        closeKeyBoard(this)
    }

    private fun onClick() {
        binding.btnSignIn.setOnClickListener {
            if (validateForm()) {
                try {
                    Thread {
                        loginUser()
                    }.start()
                } catch (exception: Exception) {
                    print(exception)
                }
            } else {
                showToast("Sign In Failed")
            }
        }
        binding.btnForgotPassword.setOnClickListener {
            val forgotPasswordIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(forgotPasswordIntent)
        }
        binding.imgBtnFacebook.setOnClickListener {
            showToast("Facebook")
        }
        binding.imgBtnApple.setOnClickListener {
            showToast("Apple")
        }
        binding.imgBtnGoogle.setOnClickListener {
            showToast("Google")
        }
    }

    private fun loginUser() {
        val credential = JSONObject()
        credential.put("email", binding.etEmail.text)
        credential.put("password", binding.etPassword.text)
        val url = URL("https://reqres.in/api/login")
        makeApiCall("POST",url,this,credential,UserLoginSuccessModel::class.java)
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

    override fun <T> onSuccessfulResponse(data: T) {
        val dashboardIntent = Intent(this,DashboardActivity::class.java)
        startActivity(dashboardIntent)
        runOnUiThread {

            Toast.makeText(this,data.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun <T> onError(message: T) {
        runOnUiThread {
            Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.groshop.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.groshop.R
import com.example.groshop.databinding.ActivitySignInBinding
import com.example.groshop.databinding.ActivitySignUpBinding

class SignUpActivity: AppCompatActivity() {
    private  lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onClick()
    }

    private fun onClick() {
        binding.tvSignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignInActivity::class.java)
        }
    }
}
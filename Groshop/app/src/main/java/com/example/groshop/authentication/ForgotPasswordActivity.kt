package com.example.groshop.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.groshop.R
import com.example.groshop.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.toolbar.customToolbar) {
            this.navigationIcon = AppCompatResources.getDrawable(applicationContext,R.drawable.back_arrow)
            this.setNavigationOnClickListener {

                onBackPressed()
            }
        }
        onClick()
    }


    private fun onClick() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this,VerifyNumberActivity::class.java)
            startActivity(intent)
        }
    }
}
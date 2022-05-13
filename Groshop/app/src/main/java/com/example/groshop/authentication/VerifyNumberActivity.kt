package com.example.groshop.authentication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.groshop.R
import com.example.groshop.databinding.ActivityVerifyNumberBinding

class VerifyNumberActivity: AppCompatActivity() {

    private lateinit var binding: ActivityVerifyNumberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyNumberBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.toolbar.customToolbar) {
            this.navigationIcon = AppCompatResources.getDrawable(applicationContext, R.drawable.back_arrow)
            this.setNavigationOnClickListener {
                val intent = Intent(this@VerifyNumberActivity,ForgotPasswordActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.etCode1.addTextChangedListener(OTPTextWatcher(binding.etCode1,binding,this))
        binding.etCode2.addTextChangedListener(OTPTextWatcher(binding.etCode2,binding,this))
        binding.etCode3.addTextChangedListener(OTPTextWatcher(binding.etCode3,binding,this))
        binding.etCode4.addTextChangedListener(OTPTextWatcher(binding.etCode4,binding,this))
    }

}
package com.example.groshop.authentication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.example.groshop.BaseAcitivity
import com.example.groshop.R
import com.example.groshop.authentication.adapter.CustomSpinnerAdapter
import com.example.groshop.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity: BaseAcitivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private var names = ArrayList<String>()
    private var flags = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.toolbar.customToolbar) {
            this.navigationIcon = AppCompatResources.getDrawable(context,R.drawable.back_arrow)
            this.setNavigationOnClickListener {
                onBackPressed()
            }
        }
        setSpinnerData()
        onClick()
    }

    private fun setSpinnerData() {
        names.add("+91")
        names.add("+62")
        names.add("+1")
        flags.add(R.drawable.flag_icon)
        flags.add(R.drawable.flag_icon)
        flags.add(R.drawable.flag_icon)
        val adapter = CustomSpinnerAdapter(this,flags,names)
        binding.spCountryCode.adapter = adapter
    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            if(validateNumber()) {
                val intent = Intent(this, VerifyNumberActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateNumber(): Boolean {
        when {
            getValues(binding.etMobile).isEmpty() -> binding.mobileInputLayout.error = "Enter mobile number"
            getValues(binding.etMobile).length < 10 -> {
                binding.mobileInputLayout.error = "10 digits required"
                return false
            }
            else -> {
                binding.mobileInputLayout.error = null
                return true
            }
        }
        return false
    }
}
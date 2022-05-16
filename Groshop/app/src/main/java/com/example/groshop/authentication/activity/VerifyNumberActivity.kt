package com.example.groshop.authentication.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import com.example.groshop.BaseActivity
import com.example.groshop.R
import com.example.groshop.authentication.OTPTextWatcher
import com.example.groshop.databinding.ActivityVerifyNumberBinding

class VerifyNumberActivity : BaseActivity() {

    private lateinit var binding: ActivityVerifyNumberBinding
    private lateinit var alert: AlertDialog

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyNumberBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        closeKeyBoard(this)
        with(binding.toolbar.customToolbar) {
            this.navigationIcon =
                AppCompatResources.getDrawable(applicationContext, R.drawable.back_arrow)
            this.setNavigationOnClickListener {
                onBackPressed()
            }
        }

        binding.etCode1.addTextChangedListener(OTPTextWatcher(binding.etCode1, binding, this))
        binding.etCode2.addTextChangedListener(OTPTextWatcher(binding.etCode2, binding, this))
        binding.etCode3.addTextChangedListener(OTPTextWatcher(binding.etCode3, binding, this))
        binding.etCode4.addTextChangedListener(OTPTextWatcher(binding.etCode4, binding, this))

        onClick()
    }

    private fun onClick() {
        binding.btnVerify.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val builderDialog = AlertDialog.Builder(this, R.style.CustomAlertDialog)
        val layout = layoutInflater.inflate(R.layout.dialog_success, null)
        val detail = layout.findViewById<TextView>(R.id.tvSuccessfulMessage)
        detail.text = getString(R.string.text_verify)
        val buttonValue = layout.findViewById<Button>(R.id.btnReadyToShop)
        buttonValue.text = getString(R.string.button_text_ready_to_shop)
        builderDialog.setView(layout)
        builderDialog.create()
        alert = builderDialog.show()

        buttonValue.setOnClickListener {
            alert.dismiss()
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
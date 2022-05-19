package com.example.groshop.authentication.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import com.example.groshop.BaseAcitivity
import com.example.groshop.R
import com.example.groshop.databinding.ActivityResetPasswordBinding
import com.example.groshop.home.activity.DashboardActivity

class ResetPasswordActivity : BaseAcitivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var alert: AlertDialog

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        with(binding.toolbar.customToolbar) {
            this.navigationIcon =
                AppCompatResources.getDrawable(applicationContext, R.drawable.back_arrow)
            this.setNavigationOnClickListener {
                onBackPressed()
            }
            val title = this.findViewById<TextView>(R.id.tvToolbarTitle)
            title.text = getString(R.string.button_text_reset_password)

        }
        onClick()
        closeKeyBoard(this)
        onTextChange()
    }

    private fun onTextChange() {
        binding.etReset.doOnTextChanged { text, _, _, _ ->
            when (true) {
                text.toString().isEmpty() -> binding.resetInputLayout.error = "Enter password"
                text.toString().length < 8 -> binding.resetInputLayout.error = "Password too short"
                else -> {
                    binding.resetInputLayout.error = null
                }
            }
        }
        binding.etConfirmReset.doOnTextChanged { text, _, _, _ ->
            when (true) {
                text.toString().isEmpty() -> binding.confirmResetInputLayout.error =
                    "Enter password"
                text.toString() != getValues(binding.etReset) -> binding.confirmResetInputLayout.error =
                    "Passwords don't match"
                else -> {
                    binding.confirmResetInputLayout.error = null
                }
            }
        }
    }

    private fun onClick() {
        binding.btnResetPassword.setOnClickListener {
            if (validPassword()) {
                showAlertDialog()
            }

        }
    }

    private fun validPassword(): Boolean {
        when {
            getValues(binding.etReset).isEmpty() -> binding.resetInputLayout.error =
                "Enter password"
            getValues(binding.etReset).length < 8 -> binding.resetInputLayout.error =
                "Must be at least 8 character"
            getValues(binding.etConfirmReset).isEmpty() -> binding.confirmResetInputLayout.error =
                "Enter password"
            getValues(binding.etConfirmReset) != getValues(binding.etReset) ->
                binding.confirmResetInputLayout.error = "Passwords don't match"

            else -> return true
        }
        return false
    }

    private fun showAlertDialog() {
        val builderDialog = AlertDialog.Builder(this, R.style.CustomAlertDialog)
        val layout = layoutInflater.inflate(R.layout.dialog_success, null)
        val detail = layout.findViewById<TextView>(R.id.tvSuccessfulMessage)
        detail.text = getString(R.string.text_password_change)
        val buttonValue = layout.findViewById<Button>(R.id.btnReadyToShop)
        buttonValue.text = getString(R.string.button_text_back_to_home)
        builderDialog.setView(layout)
        builderDialog.create()
        alert = builderDialog.show()
        buttonValue.setOnClickListener {
            alert.dismiss()
            val dashboardIntent = Intent(this, DashboardActivity::class.java)
            startActivity(dashboardIntent)
            finish()
        }
    }
}
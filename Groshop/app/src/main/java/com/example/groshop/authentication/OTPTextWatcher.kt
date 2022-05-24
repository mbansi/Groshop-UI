package com.example.groshop.authentication

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.groshop.R
import com.example.groshop.authentication.activity.VerifyNumberActivity
import com.example.groshop.databinding.ActivityVerifyNumberBinding

class OTPTextWatcher(
    val view: View,
    val binding: ActivityVerifyNumberBinding,
    val context: VerifyNumberActivity
) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        val count = p0.toString().length
        when (view.id) {
            R.id.etCode1 -> if (count == 1) {
                binding.etCode2.requestFocus()
            }

            R.id.etCode2 -> if (count == 1) {
                binding.etCode3.requestFocus()
            }

            R.id.etCode3 -> if (count == 1) {
                binding.etCode4.requestFocus()
            }

            R.id.etCode4 -> context.currentFocus?.let {
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }
}
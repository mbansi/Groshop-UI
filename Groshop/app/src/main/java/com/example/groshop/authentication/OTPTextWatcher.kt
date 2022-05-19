package com.example.groshop.authentication

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.groshop.R
import com.example.groshop.authentication.activity.VerifyNumberActivity
import com.example.groshop.databinding.ActivityVerifyNumberBinding

class OTPTextWatcher(val view: View, val binding: ActivityVerifyNumberBinding,val context: VerifyNumberActivity) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        val numberLength = p0.toString().length
        when (view.id) {
            R.id.etCode1 -> if (numberLength == 1) {
                binding.etCode2.requestFocus()
                return
            }
            R.id.etCode2 -> if (numberLength == 0) {
                binding.etCode1.requestFocus()
                return
            } else {
                binding.etCode3.requestFocus()
                return
            }
            R.id.etCode3 -> if (numberLength == 0) {
                binding.etCode2.requestFocus()
                return
            } else {
                binding.etCode4.requestFocus()
                return
            }
            R.id.etCode4 -> if (numberLength == 0) {
                binding.etCode3.requestFocus()
                return
            }
            else {
                context.currentFocus?.let {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
            }
        }
    }
}
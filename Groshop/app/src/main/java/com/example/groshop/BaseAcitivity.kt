package com.example.groshop

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseAcitivity: AppCompatActivity() {

    fun getValues(name: EditText): String {
        return name.text.toString()
    }

    fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
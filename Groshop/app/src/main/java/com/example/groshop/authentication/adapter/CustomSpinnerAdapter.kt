package com.example.groshop.authentication.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.groshop.R
import com.example.groshop.authentication.activity.ForgotPasswordActivity

class CustomSpinnerAdapter(val context: ForgotPasswordActivity, private val flags: ArrayList<Int>, private val names: ArrayList<String>): BaseAdapter() {
    override fun getCount(): Int {
        return flags.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val view = context.layoutInflater.inflate(R.layout.layout_countrycode, parent)
        val flag = view.findViewById<ImageView>(R.id.ivCountryFlag)
        val name = view.findViewById<TextView>(R.id.tvCountryName)
        flag.setImageResource(flags[position])
        name.text = names[position]
        return  view
    }
}
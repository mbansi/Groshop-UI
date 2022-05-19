package com.example.groshop.authentication.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.groshop.R
import com.example.groshop.authentication.activity.ForgotPasswordActivity

class CustomSpinnerAdapter(val context: ForgotPasswordActivity, val flags: ArrayList<Int>, val names: ArrayList<String>): BaseAdapter() {
    override fun getCount(): Int {
        return flags.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val view = context.layoutInflater.inflate(R.layout.layout_countrycode,null)
        val flag = view.findViewById<ImageView>(R.id.ivCountryFlag)
        val name = view.findViewById<TextView>(R.id.tvCountryName)
        flag.setImageResource(flags[position])
        name.text = names[position]
        return  view
    }
}
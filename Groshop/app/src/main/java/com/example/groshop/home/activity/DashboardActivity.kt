package com.example.groshop.home.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.groshop.BaseAcitivity
import com.example.groshop.R
import com.example.groshop.databinding.ActivityDashboardBinding
import com.example.groshop.home.fragments.HomeFragment

class DashboardActivity : BaseAcitivity() {
    private lateinit var binding: ActivityDashboardBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.setOnItemSelectedListener {
            var fragment = Fragment()
            when (it.itemId) {
                R.id.home -> fragment = HomeFragment()
                else -> Fragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
            return@setOnItemSelectedListener true
        }
    }
}
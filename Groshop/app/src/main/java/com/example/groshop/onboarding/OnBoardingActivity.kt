package com.example.groshop.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.groshop.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        binding.vpOnBoarding.adapter = OnBoardingAdapter(OnBoardingModel.getOnBoardData())
        TabLayoutMediator(binding.tab, binding.vpOnBoarding) { tab, position -> }.attach()
    }
}
package com.example.groshop.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groshop.databinding.LayoutOnboardingBinding

class OnBoardingAdapter(private val onBoardData: ArrayList<OnBoardingModel>) :
    RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(onBoardData[position]) {
                binding.ivOnBoarding.setImageResource(image)
                binding.tvOnBoardTitle.text = title
                binding.tvOnBoardDetail.text = detail
            }
        }
    }

    override fun getItemCount(): Int {
        return onBoardData.size
    }

    inner class ViewHolder(val binding: LayoutOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

}
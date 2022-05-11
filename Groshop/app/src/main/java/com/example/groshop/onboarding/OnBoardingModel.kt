package com.example.groshop.onboarding

import com.example.groshop.R

data class OnBoardingModel(val image: Int,val title: String,val detail: String) {
    companion object {
        fun getOnBoardData(): ArrayList<OnBoardingModel> {
            val data = arrayListOf<OnBoardingModel>()
            data.add(OnBoardingModel(R.drawable.onboarding_food,"Groshop","No more waiting for your groceries to be delivered"))
            data.add(OnBoardingModel(R.drawable.groshop_2,"Fresh","Delivering fresh groceries at your door step."))
            return data
        }
    }
}
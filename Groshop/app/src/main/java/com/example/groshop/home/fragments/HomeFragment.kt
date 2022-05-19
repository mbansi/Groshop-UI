package com.example.groshop.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groshop.R
import com.example.groshop.databinding.FragmentHomeBinding
import com.example.groshop.home.adapters.CategoryAdapter
import com.example.groshop.home.adapters.OptionAdapter
import com.example.groshop.home.adapters.PromotionAdapter
import com.example.groshop.home.models.CategoryModel
import com.example.groshop.home.models.PromotionModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setPromotions()
        setCategory()
        setOptions()
        return binding.root
    }

    private fun setOptions() {
        val titles = getTitles()
        binding.vpOption.adapter = OptionAdapter(this)
        TabLayoutMediator(
            binding.tabOptions,
            binding.vpOption
        ) { myTabLayout: TabLayout.Tab, position: Int ->
            myTabLayout.text = titles[position]
        }.attach()
    }

    private fun getTitles(): Array<String> {
        val titleArray = arrayOf("All","Flash Sale","Discount","Best Offer","Buy Again","New Product")
        return titleArray
    }

    private fun setCategory() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvCategory.layoutManager = layoutManager
        binding.rvCategory.adapter = CategoryAdapter(getCategories(),this)
    }

    private fun getCategories(): ArrayList<CategoryModel> {
        val categories = ArrayList<CategoryModel>()
        categories.add(CategoryModel(R.drawable.orange,"Fruit"))
        categories.add(CategoryModel(R.drawable.broccoli,"Vegetable"))
        categories.add(CategoryModel(R.drawable.category_milk_eggs,"Milk & Egg"))
        categories.add(CategoryModel(R.drawable.category_meat,"Meat"))
        categories.add(CategoryModel(R.drawable.category_more,"More"))
        return categories
    }

    private fun setPromotions() {
        binding.vpPromotions.adapter = PromotionAdapter(getPromotions())
        TabLayoutMediator(binding.tabPromotions, binding.vpPromotions) { _, _ -> }.attach()
    }

    private fun getPromotions(): ArrayList<PromotionModel> {
        val promotions = ArrayList<PromotionModel>()
        promotions.add(PromotionModel("Get special offer up to 25%","at 25 - 31 March 2022"))
        promotions.add(PromotionModel("Get bumper offer up to 35%","at 1 - 5 April 2022"))
        promotions.add(PromotionModel("Get special offer up to 15%","at 21 - 23 March 2022"))
        promotions.add(PromotionModel("Get bumper offer up to 45%","at 6 - 9 April 2022"))
        return promotions
    }

}


package com.example.groshop.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.groshop.databinding.LayoutCategoryBinding
import com.example.groshop.home.models.CategoryModel
import com.example.groshop.home.fragments.HomeFragment

class CategoryAdapter(private val categories: ArrayList<CategoryModel>, val fragment: HomeFragment): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: LayoutCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(categories[position]) {
                binding.ivCategory.setImageResource(categoryImage)
                binding.itvCategoryName.text = categoryName

                binding.llCategory.setOnClickListener {
                    if (position == categories.size - 1) {
                        Toast.makeText(fragment.requireContext(),"Under Construction",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
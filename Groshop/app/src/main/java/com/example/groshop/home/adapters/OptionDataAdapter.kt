package com.example.groshop.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groshop.databinding.LayoutOptionItemBinding
import com.example.groshop.home.models.OptionModel

class OptionDataAdapter(private val options: ArrayList<OptionModel>) :
    RecyclerView.Adapter<OptionDataAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: LayoutOptionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutOptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(options[position]) {
                binding.tvDiscount.text = discountPercentage
                binding.ivFruit.setImageResource(fruitImage)
                binding.tvFruitName.text = fruitName
                binding.tvQuantity.text = quantity
                binding.tvNormalPrice.text = normalPrice
                binding.tvDiscountPrice.text = discountPrice
            }
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}
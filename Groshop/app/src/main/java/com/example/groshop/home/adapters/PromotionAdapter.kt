package com.example.groshop.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groshop.R
import com.example.groshop.databinding.LayoutPromotionBinding
import com.example.groshop.home.models.PromotionModel

class PromotionAdapter(private val promotion: ArrayList<PromotionModel>): RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(promotion[position]) {
                binding.ivPromotion.setImageResource(R.drawable.promotions)
                binding.tvOffer.text = offer
                binding.tvOfferDate.text = offerDate
            }
        }
    }

    override fun getItemCount(): Int {
       return promotion.size
    }

    inner class ViewHolder(val binding: LayoutPromotionBinding) :
        RecyclerView.ViewHolder(binding.root)
}
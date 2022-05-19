package com.example.groshop.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.groshop.R
import com.example.groshop.databinding.LayoutOptionBinding
import com.example.groshop.home.adapters.OptionDataAdapter
import com.example.groshop.home.models.OptionModel


class OptionFragment: Fragment() {

    private lateinit var binding: LayoutOptionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutOptionBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData() {
        binding.rvOptions.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvOptions.adapter = OptionDataAdapter(getOptions())
    }

    private fun getOptions(): ArrayList<OptionModel> {
        val options = ArrayList<OptionModel>()
        options.add(OptionModel("10%", R.drawable.orange,"Orange","1 kg","$7.50","$6.75"))
        options.add(OptionModel("10%", R.drawable.watermelon,"Watermelon","1 kg","$10.30","$9.27"))
        options.add(OptionModel("10%", R.drawable.broccoli,"Brocoli","1 kg","$6.00","$5.40"))
        options.add(OptionModel("10%", R.drawable.lettuce,"Lettuce","1 kg","$6.30","$5.67"))
        options.add(OptionModel("10%", R.drawable.orange,"Orange","1 kg","$7.50","$6.75"))
        options.add(OptionModel("10%", R.drawable.watermelon,"Watermelon","1 kg","$10.30","$9.27"))
        options.add(OptionModel("20%", R.drawable.broccoli,"Brocoli","1 kg","$6.00","$5.40"))
        options.add(OptionModel("10%", R.drawable.lettuce,"Lettuce","1 kg","$6.30","$5.67"))

        return options
    }

}
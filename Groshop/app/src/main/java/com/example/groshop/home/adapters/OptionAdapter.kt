package com.example.groshop.home.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.groshop.home.fragments.OptionFragment

class OptionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            1 -> return OptionFragment()
        }
        return OptionFragment()
    }

}
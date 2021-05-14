package com.rubio.movstore.ui.movcatalogue.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rubio.movstore.ui.home.HomeFragment

class SlidersAdapter(
    private val items: ArrayList<Fragment>,
    activity: HomeFragment
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }

}
package com.codesquad.starbucks.ui.order

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codesquad.starbucks.ui.order.category.DrinkCategoryFragment
import com.codesquad.starbucks.ui.order.category.FoodCategoryFragment
import com.codesquad.starbucks.ui.order.category.ProductCategoryFragment

private const val NUM_TABS = 3

class OrderCategoryViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DrinkCategoryFragment()
            1 -> FoodCategoryFragment()
            2 -> ProductCategoryFragment()
            else -> DrinkCategoryFragment()
        }
    }
}
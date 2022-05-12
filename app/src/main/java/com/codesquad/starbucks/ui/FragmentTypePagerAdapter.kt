package com.codesquad.starbucks.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codesquad.starbucks.R
import com.codesquad.starbucks.ui.favorite.FavoriteFragment
import com.codesquad.starbucks.ui.home.HomeFragment
import com.codesquad.starbucks.ui.order.OrderFragment
import com.codesquad.starbucks.ui.pay.PayFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragmentTypePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(
    fragmentActivity
), TabLayoutMediator.TabConfigurationStrategy {

    private val numberOfPage = 4

    private val titles = listOf(
        "Home",
        "Pay",
        "Order",
        "Favorite"
    )



    override fun getItemCount(): Int {
        return numberOfPage
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> HomeFragment()
            1-> PayFragment()
            2-> OrderFragment()
            3-> FavoriteFragment()
            else-> HomeFragment()
        }
    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text= titles[position]
        when(position){
            0-> tab.setIcon(R.drawable.ic_baseline_home_24)
            1-> tab.setIcon(R.drawable.ic_baseline_payment_24)
            2-> tab.setIcon(R.drawable.ic_baseline_shopping_basket_24)
            3-> tab.setIcon(R.drawable.ic_baseline_star_24)
        }
    }
}
package com.codesquad.starbucks.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentOrderBinding
import com.google.android.material.tabs.TabLayoutMediator

class OrderFragment : Fragment() {

    private lateinit var binding:FragmentOrderBinding
    private val tabTitleArray = arrayOf(
        "음료",
        "푸드",
        "상품"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager= binding.vpOrderCategory
        viewPager.adapter= OrderCategoryViewPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabOrderCategory, viewPager){tab, position->
            tab.text= tabTitleArray[position]

        }.attach()
    }

}
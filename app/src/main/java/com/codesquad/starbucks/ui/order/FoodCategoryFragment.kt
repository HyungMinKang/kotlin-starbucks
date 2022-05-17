package com.codesquad.starbucks.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentFoodCategoryBinding
import com.codesquad.starbucks.domain.model.Category


class FoodCategoryFragment : Fragment() {

    private lateinit var binding: FragmentFoodCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_food_category, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bread= Category(Constants.BREAD, getString(R.string.label_bread_korean), getString(R.string.label_bread_english))
        val cake= Category(Constants.CAKE, getString(R.string.label_cake_korean), getString(R.string.label_cake_english))
        val sandwich= Category(Constants.SANDWICH, getString(R.string.label_sandwich_korean), getString(R.string.label_sandwich_english))
        val foodCategory= listOf(bread,cake,sandwich)
        val foodAdapter= CategoryAdapter()
        binding.rvCategoryItem.adapter= foodAdapter
        foodAdapter.submitEvents(foodCategory)
    }

}
package com.codesquad.starbucks.ui.order.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentFoodCategoryBinding
import com.codesquad.starbucks.domain.model.Category


class FoodCategoryFragment : Fragment() {

    private lateinit var binding: FragmentFoodCategoryBinding
    private lateinit var navigator: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_food_category, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator= NavHostFragment.findNavController(this)
        val bread= Category(Constants.BREAD, getString(R.string.label_bread_korean), getString(R.string.label_bread_english))
        val cake= Category(Constants.CAKE, getString(R.string.label_cake_korean), getString(R.string.label_cake_english))
        val sandwich= Category(Constants.SANDWICH, getString(R.string.label_sandwich_korean), getString(R.string.label_sandwich_english))
        val foodCategory= listOf(bread,cake,sandwich)
        val foodAdapter=  CategoryAdapter {categoryName ->  (openCategoryDetail(categoryName)) }
        binding.rvCategoryItem.adapter= foodAdapter
        foodAdapter.submitEvents(foodCategory)
    }

    private fun openCategoryDetail(categoryName:String){
        val categoryCD= when(categoryName){
            getString(R.string.label_bread_korean)->"W0000013.js"
            getString(R.string.label_cake_korean)->"W0000032.js"
            getString(R.string.label_sandwich_korean)->"W0000033.js"
            else->""
        }
        val bundle= bundleOf(Constants.CATEGORY_KEY to categoryName, Constants.CATEGORY_CD_KEY to categoryCD)
        navigator.navigate(R.id.action_navigation_order_to_categoryDetailFragment, bundle)
    }
}
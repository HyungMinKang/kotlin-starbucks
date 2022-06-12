package com.codesquad.starbucks.ui.order.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentDrinkCategoryBinding
import com.codesquad.starbucks.domain.model.Category


class DrinkCategoryFragment : Fragment() {

    private lateinit var binding: FragmentDrinkCategoryBinding
    private lateinit var navigator: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink_category, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = NavHostFragment.findNavController(this)
        val coldBrew = Category(Constants.COLD_BREW, getString(R.string.label_cold_brew_korean), getString(R.string.label_cold_brew_english))
        val espresso = Category(Constants.ESPRESSO, getString(R.string.label_espresso_korean), getString(R.string.label_espresso_english))
        val frappuccino = Category(Constants.FRAPPUCCINO, getString(R.string.label_frappuccino_korean), getString(R.string.label_frappuccino_english))
        val drinkCategory = listOf(coldBrew, espresso, frappuccino)
        val drinkAdapter = CategoryAdapter { categoryName -> (openCategoryDetail(categoryName)) }
        binding.rvCategoryItem.adapter = drinkAdapter
        drinkAdapter.submitEvents(drinkCategory)
    }


    private fun openCategoryDetail(categoryName: String) {
        val categoryCD = when (categoryName) {
            "콜드 브루" -> "W0000171.js"
            "에스프레소" -> "W0000003.js"
            "프라푸치노" -> "W0000004.js"
            else -> ""
        }
        val bundle = bundleOf(
            Constants.CATEGORY_KEY to categoryName,
            Constants.CATEGORY_CD_KEY to categoryCD
        )
        navigator.navigate(R.id.action_navigation_order_to_categoryDetailFragment, bundle)
    }
}
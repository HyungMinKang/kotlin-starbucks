package com.codesquad.starbucks.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentDrinkCategoryBinding
import com.codesquad.starbucks.domain.model.Category


class DrinkCategoryFragment : Fragment() {

    private lateinit var binding:FragmentDrinkCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_drink_category, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coldBrew= Category(Constants.COLD_BREW, getString(R.string.label_cold_brew_korean), getString(R.string.label_cold_brew_english))
        val espresso= Category(Constants.ESPRESSO, getString(R.string.label_espresso_korean), getString(R.string.label_espresso_english))
        val frappuccino= Category(Constants.FRAPPUCCINO, getString(R.string.label_frappuccino_korean), getString(R.string.label_frappuccino_english))
        val drinkCategory= listOf(coldBrew,espresso,frappuccino)
        val drinkAdapter= CategoryAdapter()
        binding.rvCategoryItem.adapter= drinkAdapter
        drinkAdapter.submitEvents(drinkCategory)
    }

}
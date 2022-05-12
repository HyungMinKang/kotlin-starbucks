package com.codesquad.starbucks.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentHomefragmentBinding
import com.codesquad.starbucks.ui.event.EventViewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomefragmentBinding
    private val viewModel: HomeViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homefragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personalRecommendAdapter= PersonalRecommendAdapter()
        viewModel.eventInfo.observe(viewLifecycleOwner){
            binding.tvHomePersonalRecommendTitle.text = it.displayName
            viewModel.getProduct(it.personalRecommendProducts)
        }

        binding.rvHomePersonalRecommendMenus.apply {
            adapter= personalRecommendAdapter
        }

        viewModel.products.observe(viewLifecycleOwner){
            println(it)
            personalRecommendAdapter.submitProducts(it)
        }
    }



}
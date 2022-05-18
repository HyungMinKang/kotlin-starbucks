package com.codesquad.starbucks.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentCategoryDetailBinding
import org.koin.android.ext.android.inject


class CategoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCategoryDetailBinding
    private val viewModel: CategoryDetailViewModel by inject()
    private val categoryName: String by lazy { requireArguments().getString(Constants.CATEGORY_KEY, Constants.EMPTY_DEFAULT) }
    private val categoryCD: String by lazy { requireArguments().getString(Constants.CATEGORY_CD_KEY, Constants.EMPTY_DEFAULT) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryDetailAdapter= CategoryItemAdapter()
        viewModel.getCategoryItems(categoryCD)
        binding.tvCategoryDetailTitle.text= categoryName
        binding.rvCategoryItem.adapter= categoryDetailAdapter
        viewModel.items.observe(viewLifecycleOwner){
            categoryDetailAdapter.submitEvents(it)
        }
        binding.btnCategoryItemBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

    }

}
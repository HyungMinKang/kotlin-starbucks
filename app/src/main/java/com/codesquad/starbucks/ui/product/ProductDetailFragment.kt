package com.codesquad.starbucks.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentProductDetailBinding
import org.koin.android.ext.android.inject


class ProductDetailFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailBinding
    private val productPrice: String by lazy { requireArguments().getString(Constants.PRODUCT_PRICE_KEY, Constants.EMPTY_DEFAULT) }
    private val productCD: String by lazy { requireArguments().getString(Constants.PRODUCT_CD_KEY, Constants.EMPTY_DEFAULT) }
    private val viewModel:ProductDetailViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.price= "${productPrice}원"
        println(productPrice)
        println(productCD)
        viewModel.getProductInfo(productCD)
        viewModel.productInfo.observe(viewLifecycleOwner){
            binding.product= it
        }
        viewModel.productImage.observe(viewLifecycleOwner){
            binding.imageUrl= it
        }

        binding.btnProductDetailBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }


}
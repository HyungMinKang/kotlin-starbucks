package com.codesquad.starbucks.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentProductDetailBinding
import com.codesquad.starbucks.ui.common.FlowExtension.setClickEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val productPrice: String by lazy { requireArguments().getString(Constants.PRODUCT_PRICE_KEY, Constants.EMPTY_DEFAULT) }
    private val productCD: String by lazy { requireArguments().getString(Constants.PRODUCT_CD_KEY, Constants.EMPTY_DEFAULT)}
    private val viewModel: ProductDetailViewModel by inject()
    //private val db = FavoriteDataBase.getInstance(requireContext().applicationContext)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.price = "${productPrice}원"
        viewModel.getProductInfo(productCD)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productInfo.collect { binding.product = it }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productImage.collect{ binding.imageUrl = it }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productImage.collect { binding.imageUrl = it }
        }

        binding.btnProductDetailBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnProductDetailFavorite.setClickEvent(lifecycleScope ) {
             uiUpdate(binding.btnProductDetailFavorite)
        }
    }

    private fun uiUpdate(button: Button) {
        button.isSelected = !(button.isSelected)
    }

//    private fun dbUpdate() {
//        val favoriteItem = FavoriteEntity(
//            binding.product!!.koTitle,
//            binding.product!!.enTitle,
//            binding.price!!,
//            binding.imageUrl!!
//        )
//        val isFavorite = binding.btnProductDetailFavorite.isSelected
//        CoroutineScope(Dispatchers.IO).launch {
//            db?.let {
//                it.favoriteDao().insert(favoriteItem)
//
//            }
//        }

    }


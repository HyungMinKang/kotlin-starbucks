package com.codesquad.starbucks.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.ProductDetail
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _productInfo = MutableLiveData<ProductDetail>()
    val productInfo: LiveData<ProductDetail> = _productInfo

    private val _productImage= MutableLiveData<String>()
    val productImage:LiveData<String> = _productImage

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }


    fun getProductInfo(productCD: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getProductDetail(productCD)
                .onSuccess {
                    println(it)
                    _productInfo.value = it
                }
            homeRepository.getProductFile(productCD)
                .onSuccess {
                    println(it)
                    _productImage.value = it
                }
        }
    }

}
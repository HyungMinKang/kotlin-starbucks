package com.codesquad.starbucks.ui.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.ProductDetail
import com.codesquad.starbucks.room.FavoriteEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _productInfo = MutableStateFlow<ProductDetail>(ProductDetail())
    val productInfo: StateFlow<ProductDetail> = _productInfo

    private val _productImage = MutableStateFlow<String>("")
    val productImage: StateFlow<String> = _productImage

    private val _favoriteRegistered = MutableStateFlow<Boolean>(false)
    val favoriteRegistered: StateFlow<Boolean> = _favoriteRegistered


    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    fun getProductInfo(productCD: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getProductDetail(productCD)
                .catch { error -> _errorMessage.value = error.message.toString() }
                .collect {
                    _productInfo.value = it?:ProductDetail()
                }

            homeRepository.getProductFile(productCD)
                .catch { error -> _errorMessage.value = error.message.toString() }
                .collect {
                    _productImage.value = it
                }
        }
    }

    fun searchFavorite(favoriteKoTitle: String) {
        CoroutineScope(Dispatchers.IO).launch  {
            homeRepository.getFavoriteItem(favoriteKoTitle)
                .collect {
                    _favoriteRegistered.value = it != null
                }
        }
    }

    fun addFavorite(favoriteEntity: FavoriteEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            homeRepository.addFavoriteItem(favoriteEntity)
        }
    }

    fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        CoroutineScope(Dispatchers.IO).launch  {
            homeRepository.deleteFavoriteItem(favoriteEntity)
        }
    }

}
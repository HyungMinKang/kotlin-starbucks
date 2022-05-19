package com.codesquad.starbucks.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.remote.event.EventApi
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.HomeContent
import com.codesquad.starbucks.domain.model.HomeEvent
import com.codesquad.starbucks.domain.model.NowRecommendItem
import com.codesquad.starbucks.domain.model.PersoanlRecommendItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _totalInfo =
        MutableStateFlow<HomeContent>(HomeContent("", emptyList(), "", "", emptyList()))
    val eventInfo: StateFlow<HomeContent> = _totalInfo

    private val _products = MutableStateFlow<MutableList<PersoanlRecommendItem>>(mutableListOf())
    val products: StateFlow<MutableList<PersoanlRecommendItem>> = _products

    private val _events = MutableStateFlow<List<HomeEvent>>(emptyList())
    val events: StateFlow<List<HomeEvent>> = _events

    private val _nowProducts = MutableStateFlow<MutableList<NowRecommendItem>>(mutableListOf())
    val nowProducts: StateFlow<MutableList<NowRecommendItem>> = _nowProducts

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    private fun getHomeContent() {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getTotalInfo()
                .catch { error -> _errorMessage.value = error.message.toString() }
                .collect { _totalInfo.value = it }
        }
    }

    fun getProduct(product_list: List<String>) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val tempProducts = mutableSetOf<PersoanlRecommendItem>()
            var tempProductTitle = ""
            var tempProductImage = ""
            val loadProduct = launch {
                for (product in product_list) {
                    async {
                        homeRepository.getProductTitle(product)
                            .catch { error -> _errorMessage.value = error.message.toString() }
                            .collect { tempProductTitle = it }
                    }.await()

                    async {
                        homeRepository.getProductFile(product)
                            .catch { error -> _errorMessage.value = error.message.toString() }
                            .collect { tempProductImage = it }
                    }.await()
                    if (tempProductTitle.isNotEmpty() && tempProductImage.isNotEmpty()) {
                        tempProducts.add(PersoanlRecommendItem(tempProductTitle, tempProductImage))
                    }
                }
                _products.value = tempProducts.toMutableList()
            }.join()
        }
    }

    fun getNowRecommendProduct(product_list: List<String>) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val tempProducts = mutableSetOf<NowRecommendItem>()
            var tempProductTitle = ""
            var tempProductImage = ""
            val loadProduct = launch {
                for (product in product_list) {
                    async {
                        homeRepository.getNowProductTitle(product)
                            .catch { error -> _errorMessage.value = error.message.toString() }
                            .collect { tempProductTitle = it }
                    }.await()

                    async {
                        homeRepository.getNowProductFile(product)
                            .catch { error -> _errorMessage.value = error.message.toString() }
                            .collect { tempProductImage = it }
                    }.await()
                    if (tempProductTitle.isNotEmpty() && tempProductImage.isNotEmpty()) { tempProducts.add(NowRecommendItem(tempProductTitle, tempProductImage)) }
                }
                _nowProducts.value = tempProducts.toMutableList()
            }.join()
        }
    }

    fun getHomeEvents() {
        viewModelScope.launch(coroutineExceptionHandler) {
            launch {
                homeRepository.getHomeEvents("all")
                    .catch { error -> _errorMessage.value = error.message.toString() }
                    .collect { _events.value = it }
            }
        }
    }

    init {
        getHomeContent()
    }
}
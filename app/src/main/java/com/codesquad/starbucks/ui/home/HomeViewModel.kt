package com.codesquad.starbucks.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.remote.event.EventApi
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.HomeContent
import com.codesquad.starbucks.domain.model.HomeEvent
import com.codesquad.starbucks.domain.model.PersoanlRecommendItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _totalInfo = MutableLiveData<HomeContent>()
    val eventInfo: LiveData<HomeContent> = _totalInfo

    private val _products = MutableLiveData<MutableList<PersoanlRecommendItem>>()
    val products: LiveData<MutableList<PersoanlRecommendItem>> = _products

    private val _events= MutableLiveData<List<HomeEvent>>()
    val events : LiveData<List<HomeEvent>> = _events

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    private fun getHomeContent() {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getTotalInfo()
                .onSuccess {
                    _totalInfo.value = it
                }
                .onFailure {
                    _errorMessage.value = EventApi.ERROR_MESSAGE
                }
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
                            .onSuccess {
                                tempProductTitle = it
                            }
                            .onFailure {
                                _errorMessage.value = EventApi.ERROR_MESSAGE
                            }
                    }.await()

                    async {
                        homeRepository.getProductFile(product)
                            .onSuccess {
                                tempProductImage = it
                            }.onFailure {
                                _errorMessage.value = EventApi.ERROR_MESSAGE
                            }
                    }.await()
                    if (tempProductTitle.isNotEmpty() && tempProductImage.isNotEmpty()) {
                        tempProducts.add(PersoanlRecommendItem(tempProductTitle, tempProductImage))
                    }
                }

                _products.value = tempProducts.toMutableList()
            }.join()
        }
    }

    fun getHomeEvents() {
        viewModelScope.launch(coroutineExceptionHandler) {
            launch {
                homeRepository.getHomeEvents("all")
                    .onSuccess {
                        _events.value = it
                    }
                    .onFailure {
                        _errorMessage.value = EventApi.ERROR_MESSAGE
                    }
            }
        }
    }

    init {
        getHomeContent()
    }
}
package com.codesquad.starbucks.ui.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.CategoryItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CategoryDetailViewModel(private val homeRepository: HomeRepository):ViewModel() {

    private val _items= MutableLiveData<List<CategoryItem>>()
    val items:LiveData<List<CategoryItem>> = _items

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    fun getCategoryItems(jsFileName:String){
        viewModelScope.launch (coroutineExceptionHandler){
            homeRepository.getCategoryItems(jsFileName)
                .onSuccess {
                    println(it)
                    _items.value= it
                }
                .onFailure {
                    println(it)
                }
        }
    }
}
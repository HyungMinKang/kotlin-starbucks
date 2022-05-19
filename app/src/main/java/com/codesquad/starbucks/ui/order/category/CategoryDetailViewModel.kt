package com.codesquad.starbucks.ui.order.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.CategoryItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoryDetailViewModel(private val homeRepository: HomeRepository):ViewModel() {

    private val _items= MutableStateFlow<List<CategoryItem>>(emptyList())
    val items: StateFlow<List<CategoryItem>> = _items

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    fun getCategoryItems(jsFileName:String){
        viewModelScope.launch (coroutineExceptionHandler){
            homeRepository.getCategoryItems(jsFileName)
                .catch { error -> _errorMessage.value = error.message.toString() }
                .collect {
                    _items.value= it
                }
        }
    }
}
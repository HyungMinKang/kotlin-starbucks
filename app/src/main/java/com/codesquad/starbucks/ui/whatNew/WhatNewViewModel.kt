package com.codesquad.starbucks.ui.whatNew

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.WhatNewEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class WhatNewViewModel(private val homeRepository: HomeRepository):ViewModel() {

    private val _events= MutableLiveData<List<WhatNewEvent>>()
    val events:LiveData<List<WhatNewEvent>> = _events

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage


    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }


    private fun getWhatNewEvent(){
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getWhatNewEvents()
                .onSuccess {
                    _events.value= it
                }
        }
    }

    init {
        getWhatNewEvent()
    }
}
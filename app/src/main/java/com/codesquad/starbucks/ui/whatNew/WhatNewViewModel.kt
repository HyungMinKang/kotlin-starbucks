package com.codesquad.starbucks.ui.whatNew

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.WhatNewEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WhatNewViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _events = MutableStateFlow<List<WhatNewEvent>>(emptyList())
    val events: StateFlow<List<WhatNewEvent>> = _events

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    private fun getWhatNewEvent() {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getWhatNewEvents()
                .catch { error -> _errorMessage.value = error.message.toString() }
                .collect { _events.value = it }
        }
    }

    init {
        getWhatNewEvent()
    }
}
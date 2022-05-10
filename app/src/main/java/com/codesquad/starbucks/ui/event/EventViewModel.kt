package com.codesquad.starbucks.ui.event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.remote.event.EventApi
import com.codesquad.starbucks.domain.EventRepository
import com.codesquad.starbucks.domain.model.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    private val _eventInfo = MutableLiveData<Event>()
    val eventInfo: LiveData<Event> = _eventInfo

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(Constants.COROUTINE_EXCEPTION_TAG, ": ${throwable.message}")
            _errorMessage.value = Constants.COROUTINE_ERROR
        }

    private fun getEvent(){
        viewModelScope.launch(coroutineExceptionHandler) {
            eventRepository.getEvent()
                .onSuccess {
                    println(it)
                    _eventInfo.value= it
                }
                .onFailure {
                    _errorMessage.value= EventApi.ERROR_MESSAGE
                }

        }
    }

    init {
        getEvent()
    }
}
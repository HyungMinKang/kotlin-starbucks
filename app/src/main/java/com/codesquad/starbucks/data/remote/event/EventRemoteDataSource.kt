package com.codesquad.starbucks.data.remote.event

import android.util.Log
import com.codesquad.starbucks.data.dto.EventDto

class EventRemoteDataSource(private val api: EventApi  ) : EventDataSource {

    override suspend fun getEvent(): EventDto {
        Log.d("TEST", "${api.getEvent()}")
        return api.getEvent()
    }

}
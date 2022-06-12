package com.codesquad.starbucks.data.remote.event

import com.codesquad.starbucks.data.dto.EventDto

class EventRemoteDataSource(private val api: EventApi) : EventDataSource {

    override suspend fun getEvent(): EventDto = api.getEvent()

}
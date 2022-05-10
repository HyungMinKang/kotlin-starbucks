package com.codesquad.starbucks.data

import com.codesquad.starbucks.data.dto.toEvent
import com.codesquad.starbucks.data.remote.event.EventDataSource
import com.codesquad.starbucks.domain.EventRepository
import com.codesquad.starbucks.domain.model.Event

class EventRepositoryImpl(private val eventDataSource: EventDataSource) : EventRepository {
    override suspend fun getEvent(): Result<Event> {
        return kotlin.runCatching {
            val response = eventDataSource.getEvent()
            response.toEvent()
        }
    }

}
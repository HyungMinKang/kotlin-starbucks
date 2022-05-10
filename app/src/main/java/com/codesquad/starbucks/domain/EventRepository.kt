package com.codesquad.starbucks.domain

import com.codesquad.starbucks.domain.model.Event

interface EventRepository {

    suspend fun getEvent():Result<Event>
}
package com.codesquad.starbucks.data.remote.event

import com.codesquad.starbucks.data.dto.EventDto

interface EventDataSource {

    suspend fun getEvent(): EventDto
}
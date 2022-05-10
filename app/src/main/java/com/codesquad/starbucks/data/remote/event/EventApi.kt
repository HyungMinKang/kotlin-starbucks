package com.codesquad.starbucks.data.remote.event

import com.codesquad.starbucks.data.dto.EventDto
import retrofit2.http.GET

interface EventApi {

    @GET("starbuckst-loading.json")
    suspend fun getEvent(): EventDto

    companion object {
        const val ERROR_MESSAGE = "서버에 접속을 실패했습니다"
    }
}
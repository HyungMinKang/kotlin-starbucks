package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.HomeContentDto
import com.codesquad.starbucks.data.dto.ProductInfoDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeContentApi {

    @GET(".")
    suspend fun getTotal():HomeContentDto


}
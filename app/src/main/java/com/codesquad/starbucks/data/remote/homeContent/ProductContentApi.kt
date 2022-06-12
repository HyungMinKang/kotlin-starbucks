package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductContentApi {
    @FormUrlEncoded
    @POST("menu/productViewAjax.do")
    suspend fun getProductInfo(@Field("product_cd") product_cd: String): ProductInfoDto

    @FormUrlEncoded
    @POST("menu/productFileAjax.do")
    suspend fun getProductImage(@Field("PRODUCT_CD") product_cd: String): ProductFileDto

    @FormUrlEncoded
    @POST("whats_new/getIngList.do")
    suspend fun getAllEvents(@Field("MENU_CD") menu_cd: String): HomeEventsDto

    @POST("whats_new/newsListAjax.do")
    suspend fun getWhatNewEvents(): WhatNewEventDto

    @POST("upload/json/menu/{jsFileName}")
    suspend fun getCategoryItem(@Path("jsFileName") jsFileName: String): CategoryItemDto
}
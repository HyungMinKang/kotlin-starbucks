package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.*

interface HomeContentDataSource {
    suspend fun getTotal():HomeContentDto

    suspend fun getProductInfo(product_cd:String):ProductInfoDto

    suspend fun getProductImage(product_cd: String):ProductFileDto

    suspend fun getHomeEvents(menu_cd:String):HomeEventsDto

    suspend fun getWhatNewEvents():WhatNewEventDto

    suspend fun getCategoryItems(jsFileName:String):CategoryItemDto
}
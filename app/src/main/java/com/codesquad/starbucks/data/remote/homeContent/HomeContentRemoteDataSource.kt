package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.*

class HomeContentRemoteDataSource(private val api: HomeContentApi, private val productApi:ProductContentApi):HomeContentDataSource {
    override suspend fun getTotal(): HomeContentDto = api.getTotal()
    override suspend fun getProductInfo(product_cd:String): ProductInfoDto = productApi.getProductInfo(product_cd)
    override suspend fun getProductImage(product_cd: String): ProductFileDto = productApi.getProductImage(product_cd)
    override suspend fun getHomeEvents(menu_cd: String): HomeEventsDto= productApi.getAllEvents(menu_cd)
    override suspend fun getWhatNewEvents(): WhatNewEventDto =productApi.getWhatNewEvents()

}
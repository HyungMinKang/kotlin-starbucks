package com.codesquad.starbucks.data

import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.dto.toHomeContent
import com.codesquad.starbucks.data.dto.toProductDetail
import com.codesquad.starbucks.data.remote.homeContent.HomeContentDataSource
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(private val homeContentDataSource: HomeContentDataSource) :
    HomeRepository {

    override suspend fun getTotalInfo(): Flow<HomeContent> {
        val response = homeContentDataSource.getTotal()
        return flow { emit(response.toHomeContent()) }
    }

    override suspend fun getProductTitle(product_cd: String): Flow<String> {
        val response = homeContentDataSource.getProductInfo(product_cd)
        return flow { emit(response.view.productNM) }
    }

    override suspend fun getProductFile(product_cd: String): Flow<String> {
        val response = homeContentDataSource.getProductImage(product_cd)
        return flow { emit("${response.file[0].imgUPLOADPATH}${response.file[0].filePATH}") }
    }

    override suspend fun getNowProductTitle(product_cd: String): Flow<String> {
        val response = homeContentDataSource.getProductInfo(product_cd)
        return flow { emit(response.view.productNM) }
    }

    override suspend fun getNowProductFile(product_cd: String): Flow<String> {
        val response = homeContentDataSource.getProductImage(product_cd)
        return flow { emit("${response.file[0].imgUPLOADPATH}${response.file[0].filePATH}") }
    }

    override suspend fun getHomeEvents(menu_cd: String): Flow<List<HomeEvent>> {
        val response = homeContentDataSource.getHomeEvents(menu_cd)
        val homeEvents = response.list.map {
            val url = "${Constants.IMAGE_UPLOAD_PATH}${Constants.IMAGE_PROMOTION_PATH}${it.mobTHUM}"
            HomeEvent(it.title, url)
        }
        return flow { emit(homeEvents) }
    }

    override suspend fun getWhatNewEvents(): Flow<List<WhatNewEvent>> {
        val response = homeContentDataSource.getWhatNewEvents()
        val whatNewEvent = response.list.map {
            val url = "${Constants.IMAGE_UPLOAD_PATH}${Constants.IMAGE_NEWS_PATH}${it.appThnlImgName}"
            WhatNewEvent(it.title, it.newsDt, url)
        }
        return flow { emit(whatNewEvent) }
    }

    override suspend fun getCategoryItems(jsFileName: String): Flow<List<CategoryItem>> {
        val response = homeContentDataSource.getCategoryItems(jsFileName)
        val categoryItems = response.list.map {
            val url = "${Constants.IMAGE_UPLOAD_PATH}${it.filePATH}"
            val randomPrice = (3500..6000).random()
            CategoryItem(url, it.productNM, it.productNM, it.productCD, randomPrice)
        }
        return flow { emit(categoryItems) }
    }

    override suspend fun getProductDetail(product_cd: String): Flow<ProductDetail> {
        val response = homeContentDataSource.getProductInfo(product_cd)
        return flow { emit(response.view.toProductDetail()) }
    }
}

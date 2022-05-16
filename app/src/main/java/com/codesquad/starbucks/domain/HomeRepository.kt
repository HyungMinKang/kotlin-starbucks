package com.codesquad.starbucks.domain

import com.codesquad.starbucks.domain.model.HomeContent
import com.codesquad.starbucks.domain.model.HomeEvent

interface HomeRepository {

    suspend fun getTotalInfo():Result<HomeContent>

    suspend fun getProductTitle(product_cd:String):Result<String>

    suspend fun getProductFile(product_cd: String):Result<String>

    suspend fun getHomeEvents(menu_cd:String):Result<List<HomeEvent>>
}
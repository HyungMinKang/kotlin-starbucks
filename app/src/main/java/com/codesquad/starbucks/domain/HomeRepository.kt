package com.codesquad.starbucks.domain

import com.codesquad.starbucks.domain.model.CategoryItem
import com.codesquad.starbucks.domain.model.HomeContent
import com.codesquad.starbucks.domain.model.HomeEvent
import com.codesquad.starbucks.domain.model.WhatNewEvent

interface HomeRepository {

    suspend fun getTotalInfo():Result<HomeContent>

    suspend fun getProductTitle(product_cd:String):Result<String>

    suspend fun getProductFile(product_cd: String):Result<String>

    suspend fun getHomeEvents(menu_cd:String):Result<List<HomeEvent>>

    suspend fun getWhatNewEvents():Result<List<WhatNewEvent>>

    suspend fun getCategoryItems(jsFileName:String):Result<List<CategoryItem>>
}
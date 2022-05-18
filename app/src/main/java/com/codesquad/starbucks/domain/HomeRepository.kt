package com.codesquad.starbucks.domain

import com.codesquad.starbucks.domain.model.*

interface HomeRepository {

    suspend fun getTotalInfo():Result<HomeContent>

    suspend fun getProductTitle(product_cd:String):Result<String>

    suspend fun getProductFile(product_cd: String):Result<String>

    suspend fun getHomeEvents(menu_cd:String):Result<List<HomeEvent>>

    suspend fun getWhatNewEvents():Result<List<WhatNewEvent>>

    suspend fun getCategoryItems(jsFileName:String):Result<List<CategoryItem>>

    suspend fun getProductDetail(product_cd: String):Result<ProductDetail>
}
package com.codesquad.starbucks.domain

import com.codesquad.starbucks.domain.model.*
import com.codesquad.starbucks.room.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getTotalInfo(): Flow<HomeContent>

    suspend fun getProductTitle(product_cd: String): Flow<String?>

    suspend fun getProductFile(product_cd: String): Flow<String>

    suspend fun getNowProductTitle(product_cd: String): Flow<String?>

    suspend fun getNowProductFile(product_cd: String): Flow<String>

    suspend fun getHomeEvents(menu_cd: String): Flow<List<HomeEvent>>

    suspend fun getWhatNewEvents(): Flow<List<WhatNewEvent>>

    suspend fun getCategoryItems(jsFileName: String): Flow<List<CategoryItem>>

    suspend fun getProductDetail(product_cd: String): Flow<ProductDetail?>

    suspend fun getFavoriteItem(favoriteItemTitle: String): Flow<FavoriteEntity?>

    suspend fun addFavoriteItem(favoriteEntity: FavoriteEntity)

    suspend fun deleteFavoriteItem(favoriteEntity: FavoriteEntity)

    suspend fun getAllFavoriteItem(): Flow<List<FavoriteEntity>>
}
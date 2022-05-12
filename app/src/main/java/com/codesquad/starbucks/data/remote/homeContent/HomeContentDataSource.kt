package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.HomeContentDto
import com.codesquad.starbucks.data.dto.ProductFileDto
import com.codesquad.starbucks.data.dto.ProductInfoDto

interface HomeContentDataSource {
    suspend fun getTotal():HomeContentDto

    suspend fun getProductInfo(product_cd:String):ProductInfoDto

    suspend fun getProductImage(product_cd: String):ProductFileDto
}
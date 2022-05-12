package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.HomeContentDto
import com.codesquad.starbucks.data.dto.ProductFileDto
import com.codesquad.starbucks.data.dto.ProductInfoDto

class HomeContentRemoteDataSource(private val api: HomeContentApi, private val productApi:ProductContentApi):HomeContentDataSource {
    override suspend fun getTotal(): HomeContentDto = api.getTotal()
    override suspend fun getProductInfo(product_cd:String): ProductInfoDto = productApi.getProductInfo(product_cd)
    override suspend fun getProductImage(product_cd: String): ProductFileDto = productApi.getProductImage(product_cd)
}
package com.codesquad.starbucks.data.remote.homeContent

import com.codesquad.starbucks.data.dto.ProductFileDto
import com.codesquad.starbucks.data.dto.ProductInfoDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProductContentApi {
    @FormUrlEncoded
    @POST("menu/productViewAjax.do")
    suspend fun getProductInfo(@Field("product_cd") product_cd:String): ProductInfoDto

    @FormUrlEncoded
    @POST("menu/productFileAjax.do")
    suspend fun getProductImage(@Field("PRODUCT_CD") product_cd:String): ProductFileDto
}
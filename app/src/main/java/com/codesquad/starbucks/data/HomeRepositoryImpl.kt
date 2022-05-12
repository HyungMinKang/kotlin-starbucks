package com.codesquad.starbucks.data

import com.codesquad.starbucks.data.dto.toHomeContent
import com.codesquad.starbucks.data.remote.homeContent.HomeContentDataSource
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.HomeContent

class HomeRepositoryImpl(private val homeContentDataSource: HomeContentDataSource):HomeRepository {
    override suspend fun getTotalInfo(): Result<HomeContent>{
        return runCatching {
            val response = homeContentDataSource.getTotal()
            response.toHomeContent()
        }
    }

    override suspend fun getProductTitle(product_cd:String): Result<String> {
        return runCatching {
            val response= homeContentDataSource.getProductInfo(product_cd)
            response.view.productNM
        }
    }

    override suspend fun getProductFile(product_cd: String): Result<String> {
        return runCatching {
            val response= homeContentDataSource.getProductImage(product_cd)
            "${response.file[0].imgUPLOADPATH}${response.file[0].filePATH}"
        }
    }
}
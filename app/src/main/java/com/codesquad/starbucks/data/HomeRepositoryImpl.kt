package com.codesquad.starbucks.data

import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.dto.toHomeContent
import com.codesquad.starbucks.data.remote.homeContent.HomeContentDataSource
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.domain.model.HomeContent
import com.codesquad.starbucks.domain.model.HomeEvent

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

    override suspend fun getHomeEvents(menu_cd: String): Result<List<HomeEvent>> {
        return runCatching {
            val response = homeContentDataSource.getHomeEvents(menu_cd)
            val homeEvents= response.list.map {
                val url= "${Constants.IMAGE_UPLOAD_PATH}${Constants.IMAGE_PROMOTION_PATH}${it.mobTHUM}"
                println(url)
                HomeEvent(it.title, url)
            }
            homeEvents
        }
    }
}
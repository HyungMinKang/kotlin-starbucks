package com.codesquad.starbucks.data.dto


import com.codesquad.starbucks.domain.model.HomeContent
import com.squareup.moshi.Json

data class HomeContentDto(
    @Json(name = "display-name")
    val displayName: String,
    @Json(name = "main-event")
    val mainEvent: MainEvent,
    @Json(name = "now-recommand")
    val nowRecommand: NowRecommand,
    @Json(name = "your-recommand")
    val yourRecommand: YourRecommand
)


data class MainEvent(
    @Json(name = "img_UPLOAD_PATH")
    val imgUPLOADPATH: String,
    @Json(name = "mob_THUM")
    val mobTHUM: String
)

data class NowRecommand(
    @Json(name = "products")
    val products: List<String>
)
data class YourRecommand(
    @Json(name = "products")
    val products: List<String>
)

fun HomeContentDto.toHomeContent():HomeContent= HomeContent(displayName, yourRecommand.products, mainEvent.imgUPLOADPATH, mainEvent.mobTHUM, nowRecommand.products)
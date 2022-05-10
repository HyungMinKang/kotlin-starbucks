package com.codesquad.starbucks.data.dto


import com.codesquad.starbucks.domain.model.Event
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    @Json(name = "description")
    val description: String,
    @Json(name = "event-products")
    val eventProducts: String,
    @Json(name = "range")
    val range: String,
    @Json(name = "target")
    val target: String,
    @Json(name = "title")
    val title: String
)

fun EventDto.toEvent():Event =  Event(title, range, target, description, eventProducts)
package com.codesquad.starbucks.domain.model

data class Event(
    val title: String,
    val range: String,
    val target: String,
    val description: String,
    val eventProduct: String
)
package com.codesquad.starbucks.domain.model

data class HomeContent(
    val displayName: String,
    val personalRecommendProducts: List<String>,
    val mainEventPath: String,
    val mainEventImagePath: String,
    val nowRecommendProducts: List<String>
)

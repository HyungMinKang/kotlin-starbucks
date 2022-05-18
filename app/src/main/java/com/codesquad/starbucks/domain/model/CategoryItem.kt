package com.codesquad.starbucks.domain.model

data class CategoryItem(
    val imageUrl: String,
    val koTitle: String,
    val enTitle: String,
    val productCD: String,
    val price: Int
)

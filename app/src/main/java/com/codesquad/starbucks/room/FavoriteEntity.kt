package com.codesquad.starbucks.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val koTitle: String,
    val enTitle: String,
    val price: String,
    val imageUrl: String,
)



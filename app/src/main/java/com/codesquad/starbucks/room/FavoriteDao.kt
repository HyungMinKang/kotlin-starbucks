package com.codesquad.starbucks.room

import androidx.annotation.Nullable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    fun insert(favoriteItem: FavoriteEntity)

    @Delete
    fun delete(favoriteItem: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoriteEntity>

    @Query("SELECT*FROM favorites WHERE koTitle= :itemKoTitle")
    fun search(itemKoTitle: String): FavoriteEntity
}
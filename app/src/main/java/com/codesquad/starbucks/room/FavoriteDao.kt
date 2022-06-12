package com.codesquad.starbucks.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert
    fun insert(favoriteItem: FavoriteEntity)

    @Delete
    fun delete(favoriteItem: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Query("SELECT*FROM favorites WHERE koTitle= :itemKoTitle ")
    fun search(itemKoTitle: String): Flow<FavoriteEntity?>
}
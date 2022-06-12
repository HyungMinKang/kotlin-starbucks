package com.codesquad.starbucks.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var instance: FavoriteDataBase? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): FavoriteDataBase? {
            if (instance == null) {
                synchronized(FavoriteDataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDataBase::class.java,
                        "favorite_database"
                    ).build()
                }
            }
            return instance
        }
    }
}
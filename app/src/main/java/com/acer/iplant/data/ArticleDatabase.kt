package com.acer.iplant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        fun getInstance(context: Context): ArticleDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java, "Article.db"
                ).build()
            }
    }
}
package com.acer.iplant.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getArticle(): LiveData<List<ArticleEntity>>
}
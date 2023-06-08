package com.acer.iplant.remote

import com.acer.iplant.ui.article.ArticleResponse
import com.acer.iplant.ui.article.ArticleResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/news")
    fun getArticle(): Call<ArrayList<ArticleResponseItem>>
}
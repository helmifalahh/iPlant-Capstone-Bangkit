package com.acer.iplant.ui.article

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("ArticleResponse")
	val articleResponse: List<ArticleResponseItem?>? = null
)

data class ArticleResponseItem(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("sub_info")
	val subInfo: String? = null,

	@field:SerializedName("published")
	val published: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("article")
	val article: String? = null
)

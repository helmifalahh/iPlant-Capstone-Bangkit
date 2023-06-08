package com.acer.iplant.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "article")
class ArticleEntity (
    @field:ColumnInfo(name = "title")
    @field:PrimaryKey
    val title: String,

    @field:ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,

    @field:ColumnInfo(name = "article")
    val article: String,

    @field:ColumnInfo(name = "sub_info")
    val subInfo: String
    )

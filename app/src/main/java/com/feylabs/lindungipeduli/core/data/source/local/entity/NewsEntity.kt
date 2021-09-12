package com.feylabs.lindungipeduli.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "photo")
    val cover_pict_url: String,
    @ColumnInfo(name = "created_at")
    val created_at: String,
    @ColumnInfo(name = "is_favorite")
    var is_favorite: Boolean = false,
)
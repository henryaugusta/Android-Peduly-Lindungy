package com.feylabs.lindungipeduli.core.domain.model

data class News(
    val id:String,
    val title:String,
    val author:String,
    val content :String,
    val cover_pict_url :String,
    val created_at :String,
)
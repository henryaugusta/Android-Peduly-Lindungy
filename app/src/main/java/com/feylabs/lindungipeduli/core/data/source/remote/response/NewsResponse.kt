package com.feylabs.lindungipeduli.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

class NewsResponse : ArrayList<NewsResponse.NewsResponseItem>(){
    data class NewsResponseItem(
        @SerializedName("author")
        val author: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("photo")
        val photo: String,
        @SerializedName("photo_path")
        val photoPath: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}
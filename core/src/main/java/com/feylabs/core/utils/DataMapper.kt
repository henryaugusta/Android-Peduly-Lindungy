package com.feylabs.lindungipeduli.utils

import com.feylabs.core.data.source.local.entity.NewsEntity
import com.feylabs.core.data.source.remote.response.NewsResponse
import com.feylabs.core.domain.model.News

object DataMapper {

    fun newsEntitiesToDomain(
        input: List<com.feylabs.core.data.source.local.entity.NewsEntity>
    ) = input.map {
        com.feylabs.core.domain.model.News(
            id = it.id,
            content = it.content,
            author = it.author,
            created_at = it.created_at,
            cover_pict_url = it.cover_pict_url,
            title = it.title,
            is_favorite = it.is_favorite
        )
    }

    fun newsResponsesToEntity(
        input: List<com.feylabs.core.data.source.remote.response.NewsResponse.NewsResponseItem>
    ): List<com.feylabs.core.data.source.local.entity.NewsEntity> {
        val tempList = mutableListOf<com.feylabs.core.data.source.local.entity.NewsEntity>()

        input.map {
            val nius = com.feylabs.core.data.source.local.entity.NewsEntity(
                id = it.id.toString(),
                author = it.author.toString(),
                created_at = it.createdAt,
                content = it.content,
                cover_pict_url = it.photoPath,
                title = it.title
            )
            tempList.add(nius)
        }

        return tempList
    }


    fun newsDomainToEntity(
        input: com.feylabs.core.domain.model.News
    ): com.feylabs.core.data.source.local.entity.NewsEntity =
        com.feylabs.core.data.source.local.entity.NewsEntity(
            id = input.id.toString(),
            author = input.author.toString(),
            created_at = input.created_at,
            content = input.content,
            cover_pict_url = input.cover_pict_url,
            title = input.title
        )

}
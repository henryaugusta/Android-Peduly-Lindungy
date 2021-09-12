package com.feylabs.lindungipeduli.utils

import com.feylabs.lindungipeduli.core.data.source.local.entity.NewsEntity
import com.feylabs.lindungipeduli.core.data.source.remote.response.NewsResponse
import com.feylabs.lindungipeduli.core.domain.model.News

object DataMapper {

    fun newsEntitiesToDomain(
        input: List<NewsEntity>
    ) = input.map {
        News(
            id = it.id,
            content = it.content,
            author = it.author,
            created_at = it.created_at,
            cover_pict_url = it.cover_pict_url,
            title = it.title
        )
    }

    fun newsResponsesToEntity(
        input: List<NewsResponse.NewsResponseItem>
    ): List<NewsEntity> {
        val tempList = mutableListOf<NewsEntity>()

        input.map {
            val nius = NewsEntity(
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
        input: News
    ): NewsEntity =
        NewsEntity(
            id = input.id.toString(),
            author = input.author.toString(),
            created_at = input.created_at,
            content = input.content,
            cover_pict_url = input.cover_pict_url,
            title = input.title
        )

}
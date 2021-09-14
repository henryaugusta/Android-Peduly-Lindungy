package com.feylabs.core.domain.repository

import com.feylabs.core.data.Resource
import com.feylabs.core.domain.model.News
import io.reactivex.Flowable

interface INewsRepository {

    fun getNews(): Flowable<Resource<List<News>>>

    fun checkIfFavorite(id:String): Flowable<Boolean> // only for favorite checking

    fun getFavoriteNews(): Flowable<List<News>>

    fun setFavoriteNews(news: News, state: Boolean)

}
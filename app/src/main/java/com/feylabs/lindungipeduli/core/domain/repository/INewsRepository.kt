package com.feylabs.lindungipeduli.core.domain.repository

import com.dicoding.tourismapp.core.data.Resource
import com.feylabs.lindungipeduli.core.domain.model.News
import io.reactivex.Flowable

interface INewsRepository {

    fun getNews(): Flowable<Resource<List<News>>>

    fun getFavoriteNews(): Flowable<List<News>>

    fun setFavoriteNews(news: News, state: Boolean)

}
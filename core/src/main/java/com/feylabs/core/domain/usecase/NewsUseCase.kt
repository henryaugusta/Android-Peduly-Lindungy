package com.feylabs.core.domain.usecase

import com.feylabs.core.data.Resource
import com.feylabs.core.domain.model.News
import io.reactivex.Flowable

interface NewsUseCase {

    fun getAllNews(): Flowable<Resource<List<News>>>
    fun getFavoriteNews(): Flowable<List<News>>
    fun setFavoriteNews(news: News, state: Boolean)
    fun checkIfFavorite(id:String) : Flowable<Boolean>

}
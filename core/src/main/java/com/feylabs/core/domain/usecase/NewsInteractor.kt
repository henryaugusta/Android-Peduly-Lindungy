package com.feylabs.core.domain.usecase

import com.feylabs.core.data.Resource
import com.feylabs.core.domain.model.News
import com.feylabs.core.domain.repository.INewsRepository
import io.reactivex.Flowable

class NewsInteractor
    (private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews(): Flowable<Resource<List<News>>> {
        return newsRepository.getNews()
    }

    override fun getFavoriteNews(): Flowable<List<News>> {
        return newsRepository.getFavoriteNews()
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        newsRepository.setFavoriteNews(news, state)
    }

    override fun checkIfFavorite(id: String): Flowable<Boolean> {
        return newsRepository.checkIfFavorite(id)
    }




}
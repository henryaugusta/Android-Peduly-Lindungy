package com.feylabs.lindungipeduli.ui.news

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.feylabs.lindungipeduli.core.domain.model.News
import com.feylabs.lindungipeduli.core.domain.usecase.NewsUseCase

class NewsViewModel(val newsUseCase: NewsUseCase) : ViewModel() {
    fun addToFavorite(news:News,state:Boolean) {
        newsUseCase.setFavoriteNews(news,state)
    }

    fun checkIfFavorite(id: String) =
        LiveDataReactiveStreams.fromPublisher(newsUseCase.checkIfFavorite(id))

}
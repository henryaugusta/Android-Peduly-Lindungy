package com.feylabs.lindungipeduli.ui.news

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.feylabs.core.domain.model.News
import com.feylabs.core.domain.usecase.NewsUseCase

class NewsViewModel(val newsUseCase: com.feylabs.core.domain.usecase.NewsUseCase) : ViewModel() {
    fun addToFavorite(news: com.feylabs.core.domain.model.News, state:Boolean) {
        newsUseCase.setFavoriteNews(news,state)
    }

    fun checkIfFavorite(id: String) =
        LiveDataReactiveStreams.fromPublisher(newsUseCase.checkIfFavorite(id))

}
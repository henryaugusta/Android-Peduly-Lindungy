package com.feylabs.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.feylabs.core.domain.usecase.NewsUseCase

class FavoriteViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    val favoriteLiveData = LiveDataReactiveStreams.fromPublisher(newsUseCase.getFavoriteNews())

    fun getFavoriteNews() {
        newsUseCase.getFavoriteNews()
    }

}
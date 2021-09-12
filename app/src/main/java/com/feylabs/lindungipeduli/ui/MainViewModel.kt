package com.feylabs.lindungipeduli.ui

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.feylabs.lindungipeduli.core.domain.usecase.NewsUseCase

class MainViewModel(
    newsUseCase: NewsUseCase
) : ViewModel() {
    val news = LiveDataReactiveStreams.fromPublisher(newsUseCase.getAllNews())
}
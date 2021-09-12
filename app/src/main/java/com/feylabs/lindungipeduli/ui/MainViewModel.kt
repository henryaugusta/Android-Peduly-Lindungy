package com.feylabs.lindungipeduli.ui

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.feylabs.core.domain.usecase.NewsUseCase

class MainViewModel(
    newsUseCase: com.feylabs.core.domain.usecase.NewsUseCase
) : ViewModel() {
    val news = LiveDataReactiveStreams.fromPublisher(newsUseCase.getAllNews())
}
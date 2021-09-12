package com.feylabs.lindungipeduli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feylabs.lindungipeduli.core.domain.usecase.NewsUseCase

class HomeViewModel(
    newsUseCase: NewsUseCase
) : ViewModel() {
    val news = LiveDataReactiveStreams.fromPublisher(newsUseCase.getAllNews())
}
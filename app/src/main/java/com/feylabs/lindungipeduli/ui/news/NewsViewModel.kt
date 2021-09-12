package com.feylabs.lindungipeduli.ui.news

import androidx.lifecycle.ViewModel
import com.feylabs.lindungipeduli.core.domain.usecase.NewsUseCase

class NewsViewModel(val newsUseCase: NewsUseCase) : ViewModel() {
    fun addToFavorite(){
        newsUseCase.getAllNews()
    }

}
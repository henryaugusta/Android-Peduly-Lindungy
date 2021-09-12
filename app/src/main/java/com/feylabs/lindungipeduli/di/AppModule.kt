package com.feylabs.core.di

import com.feylabs.core.domain.usecase.NewsInteractor
import com.feylabs.core.domain.usecase.NewsUseCase
import com.feylabs.lindungipeduli.ui.home.HomeViewModel
import com.feylabs.lindungipeduli.ui.news.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { NewsViewModel(get()) }
}
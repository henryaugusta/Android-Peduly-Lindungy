package com.feylabs.lindungipeduli.core.di

import com.feylabs.lindungipeduli.core.domain.usecase.NewsInteractor
import com.feylabs.lindungipeduli.core.domain.usecase.NewsUseCase
import com.feylabs.lindungipeduli.ui.MainViewModel
import com.feylabs.lindungipeduli.ui.home.HomeViewModel
import com.feylabs.lindungipeduli.ui.news.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { NewsViewModel(get()) }
}
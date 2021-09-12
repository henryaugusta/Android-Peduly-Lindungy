package com.feylabs.lindungipeduli.core.domain.usecase

import com.dicoding.tourismapp.core.data.Resource
import com.feylabs.lindungipeduli.core.data.NewsRepository
import com.feylabs.lindungipeduli.core.data.source.remote.response.NewsResponse
import com.feylabs.lindungipeduli.core.domain.model.News
import com.feylabs.lindungipeduli.core.domain.repository.INewsRepository
import io.reactivex.Flowable

class NewsInteractor
    (private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews(): Flowable<Resource<List<News>>> {
        return newsRepository.getNews()
    }


}
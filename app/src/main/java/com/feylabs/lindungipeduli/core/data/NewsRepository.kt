package com.feylabs.lindungipeduli.core.data

import com.dicoding.tourismapp.core.data.NetworkBoundResource
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.feylabs.lindungipeduli.core.data.source.remote.RemoteDataSource
import com.feylabs.lindungipeduli.core.data.source.remote.network.ApiResponse
import com.feylabs.lindungipeduli.core.data.source.remote.response.NewsResponse
import com.feylabs.lindungipeduli.core.domain.model.News
import com.feylabs.lindungipeduli.core.domain.repository.INewsRepository
import io.reactivex.Flowable

class NewsRepository(
    val remoteDataSource: RemoteDataSource,
    val appExecutors: AppExecutors
) : INewsRepository {
    override fun getNews(): Flowable<Resource<List<News>>> =
        object :
            NetworkBoundResource<List<News>, List<NewsResponse.NewsResponseItem>>(appExecutors) {
            override fun loadFromDB(): Flowable<List<News>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                TODO("Not yet implemented")
            }

            override fun createCall(): Flowable<ApiResponse<List<NewsResponse.NewsResponseItem>>> {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(data: List<NewsResponse.NewsResponseItem>) {
                TODO("Not yet implemented")
            }

        }.asFlowable()


}
package com.feylabs.lindungipeduli.core.data

import com.dicoding.tourismapp.core.data.NetworkBoundResource
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.feylabs.lindungipeduli.core.data.source.local.LocalDataSource
import com.feylabs.lindungipeduli.core.data.source.remote.RemoteDataSource
import com.feylabs.lindungipeduli.core.data.source.remote.network.ApiResponse
import com.feylabs.lindungipeduli.core.data.source.remote.response.NewsResponse
import com.feylabs.lindungipeduli.core.domain.model.News
import com.feylabs.lindungipeduli.core.domain.repository.INewsRepository
import com.feylabs.lindungipeduli.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepository(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource,
    val appExecutors: AppExecutors
) : INewsRepository {

    override fun getNews(): Flowable<Resource<List<News>>> =
        object :
            NetworkBoundResource<List<News>, List<NewsResponse.NewsResponseItem>>(appExecutors) {
            override fun loadFromDB(): Flowable<List<News>> {
                return localDataSource.getAllNews().map {
                    DataMapper.newsEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): Flowable<ApiResponse<List<NewsResponse.NewsResponseItem>>> {
                return remoteDataSource.getAllNews()
            }

            override fun saveCallResult(data: List<NewsResponse.NewsResponseItem>) {
                val newsEntity = DataMapper.newsResponsesToEntity(
                    data
                )

                localDataSource.insertNews(newsEntity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

            }

        }.asFlowable()

    override fun getFavoriteNews(): Flowable<List<News>> {
        return localDataSource.getFavoriteNews().map {
            DataMapper.newsEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.newsDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(newsEntity, state) }

    }


}
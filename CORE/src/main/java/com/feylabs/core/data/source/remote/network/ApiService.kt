package com.feylabs.core.data.source.remote.network

import com.feylabs.core.data.source.remote.response.NewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("api/news/get?type=3")
    fun getNewsList(): Flowable<NewsResponse>
}

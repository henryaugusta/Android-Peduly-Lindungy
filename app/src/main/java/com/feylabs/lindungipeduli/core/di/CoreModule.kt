package com.feylabs.lindungipeduli.core.di

import androidx.room.Room
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.feylabs.lindungipeduli.core.data.NewsRepository
import com.feylabs.lindungipeduli.core.data.source.local.LocalDataSource
import com.feylabs.lindungipeduli.core.data.source.local.room.MyRoomDatabase
import com.feylabs.lindungipeduli.core.data.source.remote.RemoteDataSource
import com.feylabs.lindungipeduli.core.data.source.remote.network.ApiService
import com.feylabs.lindungipeduli.core.domain.repository.INewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<MyRoomDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MyRoomDatabase::class.java, "my-debe.db"
        ).fallbackToDestructiveMigration().build()
    }
}


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://sawit-jaya.feylabs.my.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(androidContext(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> { NewsRepository(get(), get(), get()) }
}
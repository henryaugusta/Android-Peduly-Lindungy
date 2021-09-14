package com.feylabs.core.di

import androidx.room.Room
import com.feylabs.core.utils.AppExecutors
import com.feylabs.core.data.NewsRepository
import com.feylabs.core.data.source.local.LocalDataSource
import com.feylabs.core.data.source.local.room.MyRoomDatabase
import com.feylabs.core.data.source.remote.RemoteDataSource
import com.feylabs.core.data.source.remote.network.ApiService
import com.feylabs.core.domain.repository.INewsRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MyRoomDatabase::class.java, "my-debe.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
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
        val hostname = "sawit-jaya.feylabs.my.id"

        val certificatePinner: CertificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
            .add(hostname, "sha256/IqcjJn7FefzRdSGPA+WajyJ8fTjz8LIuM+XTjXvePpg=")
            .build()

        val client1: OkHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sawit-jaya.feylabs.my.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client1)
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
package com.feylabs.lindungipeduli.core.data.source.local.room

import androidx.room.*
import com.feylabs.lindungipeduli.core.data.source.local.entity.NewsEntity
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): Flowable<List<NewsEntity>>

    @Query("SELECT * FROM news where is_favorite = 1")
    fun getFavoriteNews(): Flowable<List<NewsEntity>>

    @Query("SELECT EXISTS(SELECT * FROM news where id=:id AND is_favorite=1) ")
    fun checkIfFavorite(id: String): Flowable<Boolean>

    @Query("DELETE from news WHERE id=:id")
    fun delete(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsEntity>): Completable

    @Update
    fun updateFavoriteTourism(tourism: NewsEntity)
}
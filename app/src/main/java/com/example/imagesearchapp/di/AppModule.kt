package com.example.imagesearchapp.di

import android.content.Context
import androidx.room.Room
import com.example.imagesearchapp.common.Constants.BASE_URL
import com.example.imagesearchapp.data.dao.ImageHistoryDatabaseDao
import com.example.imagesearchapp.data.database.ImageHistoryDatabase
import com.example.imagesearchapp.data.remote.UnsplashApi
import com.example.imagesearchapp.data.repository.ImageRepositoryImpl
import com.example.imagesearchapp.domain.repository.ImageRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
// SingletonComponentにすることでモジュールのライフサイクルがアプリケーションと同じレベルになる
@InstallIn(SingletonComponent::class)
object AppModule {

    // UnsplashApiを提供するための関数
    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi {
        // UnsplashApiクラスはRetrofitサービスとしてインスタンスをビルドする必要があるので
        // RetrofitのBuilderを使用する
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
            .build().create(UnsplashApi::class.java)
    }

    // Repositoryを提供するための関数
    @Provides
    @Singleton
    fun provideImageRepository(api: UnsplashApi): ImageRepository {
        return ImageRepositoryImpl(api)
    }

    // DAOインスタンスを作成
    @Provides
    @Singleton
    fun provideImageHistoryDao(db: ImageHistoryDatabase): ImageHistoryDatabaseDao =
        db.imageHistoryDao()

    // Databaseインスタンスを作成
    @Provides
    @Singleton
    fun provideImageHistoryDatabase(@ApplicationContext context: Context): ImageHistoryDatabase =
        Room.databaseBuilder(context, ImageHistoryDatabase::class.java, "image_history_db")
            .fallbackToDestructiveMigration()
            .build()
}

package com.example.imagesearchapp.di

import com.example.imagesearchapp.common.Constants.BASE_URL
import com.example.imagesearchapp.data.remote.UnsplashApi
import com.example.imagesearchapp.data.repository.ImageRepositoryImpl
import com.example.imagesearchapp.domain.repository.ImageRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
// モジュールのライフサイクルがアプリケーションと同じレベルになる
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
}

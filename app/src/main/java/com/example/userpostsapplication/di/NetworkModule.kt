package com.example.userpostsapplication.di

import com.example.userpostsapplication.BuildConfig
import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.remote.PostApiInterface
import com.example.userpostsapplication.data.remote.RemoteDataSource
import com.example.userpostsapplication.data.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideBaseUrl() = "http://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PostApiInterface = retrofit.create(PostApiInterface::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(postApiInterface: PostApiInterface) =RemoteDataSource(postApiInterface)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          userPostsDao: UserPostsDao) =
        PostsRepository(remoteDataSource, userPostsDao)

}
package com.example.userpostsapplication.di

import android.content.Context
import androidx.room.Room
import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.database.UserPostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context) : UserPostsDatabase{
        return Room.databaseBuilder(appContext, UserPostsDatabase::class.java,"UserPostsDatabase").build()
    }

    @Provides
    fun providePostDao(userDatabase: UserPostsDatabase): UserPostsDao {
        return userDatabase.userPostsDao()
    }
}
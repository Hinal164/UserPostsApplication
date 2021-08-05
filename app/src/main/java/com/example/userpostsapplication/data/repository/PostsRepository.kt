package com.example.userpostsapplication.data.repository

import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.utils.performNetworkAndDatabaseOperation
import com.example.userpostsapplication.data.remote.RemoteDataSource
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dao: UserPostsDao
) {
    fun fetchPosts() = performNetworkAndDatabaseOperation(
        databaseQuery = { dao.getAllPosts() },
        networkCall = { remoteDataSource.fetchPosts() },
        saveCallResult = { dao.insertPosts(it) }
    )
}
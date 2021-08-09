package com.example.userpostsapplication.data.repository

import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.remote.PostApiInterface
import com.example.userpostsapplication.di.IoDispatcher
import com.example.userpostsapplication.utils.performNetworkAndDatabaseOperation
import com.example.userpostsapplication.utils.transformResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postApiInterface: PostApiInterface,
    private val dao: UserPostsDao,
    @IoDispatcher private val  dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun fetchPosts() = performNetworkAndDatabaseOperation(
        databaseQuery = { dao.getAllPosts() },
        networkCall = {transformResult {postApiInterface.fetchPosts()}},
        saveCallResult = { dao.insertPosts(it)},
        dispatcher = this.dispatcher
    )
}
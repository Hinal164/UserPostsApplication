package com.example.userpostsapplication.data.repository

import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.remote.PostApiInterface
import com.example.userpostsapplication.di.IoDispatcher
import com.example.userpostsapplication.utils.performNetworkAndDatabaseOperation
import com.example.userpostsapplication.utils.transformResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PostDetailRepository @Inject constructor(
    private val postApiInterface: PostApiInterface,
    private val userPostsDao: UserPostsDao,
    @IoDispatcher private val  dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    fun fetchUsers(userId: Int) = performNetworkAndDatabaseOperation(
        databaseQuery = { userPostsDao.getUser(userId) },
        networkCall = { transformResult { postApiInterface.fetchUsers() } },
        saveCallResult = { userPostsDao.insertUsers(it) },
        dispatcher=this.dispatcher
    )

    fun fetchCommentList(postId: Int) = performNetworkAndDatabaseOperation(
        databaseQuery = { userPostsDao.getPostComments(postId) },
        networkCall = { transformResult {postApiInterface.fetchComments()} },
        saveCallResult = { userPostsDao.insertComments(it) },
        dispatcher=this.dispatcher
    )
}
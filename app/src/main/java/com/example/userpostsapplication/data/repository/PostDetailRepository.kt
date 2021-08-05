package com.example.userpostsapplication.data.repository

import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.remote.RemoteDataSource
import com.example.userpostsapplication.utils.performNetworkAndDatabaseOperation
import javax.inject.Inject

class PostDetailRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val userPostsDao: UserPostsDao
) {

    fun fetchUsers(userId: Int) = performNetworkAndDatabaseOperation(
        databaseQuery = { userPostsDao.getUser(userId) },
        networkCall = { remoteDataSource.fetchUsers() },
        saveCallResult = { userPostsDao.insertUsers(it) }
    )

    fun fetchCommentList(postId: Int) = performNetworkAndDatabaseOperation(
        databaseQuery = { userPostsDao.getPostComments(postId) },
        networkCall = { remoteDataSource.fetchComments() },
        saveCallResult = { userPostsDao.insertComments(it) }
    )


}
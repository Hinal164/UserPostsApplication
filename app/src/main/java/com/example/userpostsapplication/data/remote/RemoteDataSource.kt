package com.example.userpostsapplication.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postApiInterface: PostApiInterface): BaseDataSource() {
    suspend fun fetchPosts() = getResult { postApiInterface.fetchPosts() }
    suspend fun fetchUsers() = getResult { postApiInterface.fetchUsers() }
    suspend fun fetchComments() = getResult { postApiInterface.fetchComments() }
}
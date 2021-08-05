package com.example.userpostsapplication.data.remote

import com.example.userpostsapplication.data.model.*
import retrofit2.Response
import retrofit2.http.GET

interface PostApiInterface {

    @GET("posts")
    suspend fun fetchPosts():Response<List<Post>>

    @GET("users")
    suspend fun fetchUsers() :Response<List<User>>

    @GET("comments")
    suspend fun fetchComments(): Response<List<Comment>>

}
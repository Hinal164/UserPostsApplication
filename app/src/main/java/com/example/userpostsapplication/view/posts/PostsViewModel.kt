package com.example.userpostsapplication.view.posts

import androidx.lifecycle.ViewModel
import com.example.userpostsapplication.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel() {

    val postList = postsRepository.fetchPosts()


}
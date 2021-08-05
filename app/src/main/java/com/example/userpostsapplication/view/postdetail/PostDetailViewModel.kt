package com.example.userpostsapplication.view.postdetail

import androidx.lifecycle.*
import com.example.userpostsapplication.data.repository.PostDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(private val postDetailRepository: PostDetailRepository) :
    ViewModel() {


    fun getSpecificUser(userId: Int) = postDetailRepository.fetchUsers(userId)


    fun getCommentList(postId: Int) = postDetailRepository.fetchCommentList(postId)

}
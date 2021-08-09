package com.example.userpostsapplication.view.postdetail

import androidx.lifecycle.*
import com.example.userpostsapplication.data.model.Comment
import com.example.userpostsapplication.data.model.User
import com.example.userpostsapplication.data.repository.PostDetailRepository
import com.example.userpostsapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(private val postDetailRepository: PostDetailRepository) :
    ViewModel() {


    private val _userId = MutableLiveData<Int>()
    private val _postId = MutableLiveData<Int>()

    val userLiveData: LiveData<Resource<User>> = _userId.switchMap { id ->
        postDetailRepository.fetchUsers(id)
    }

    val commentListLiveData: LiveData<Resource<List<Comment>>> =
        _postId.switchMap { id -> postDetailRepository.fetchCommentList(id) }


    fun setUserId(userId: Int) {
        _userId.value = userId
    }

    fun setPostId(postId: Int) {
        _postId.value = postId
    }

}
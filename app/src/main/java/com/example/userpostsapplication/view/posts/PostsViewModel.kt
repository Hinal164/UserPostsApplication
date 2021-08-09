package com.example.userpostsapplication.view.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.data.repository.PostsRepository
import com.example.userpostsapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel() {

    val postList = postsRepository.fetchPosts()

    private val _postBoolean=MutableLiveData<Boolean>()
    val postListLiveData:LiveData<Resource<List<Post>>> = _postBoolean.switchMap { isPostFetch -> postsRepository.fetchPosts() }

    fun setPostBoolean(boolean: Boolean){
        _postBoolean.value=boolean
    }

}
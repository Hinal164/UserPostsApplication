package com.example.userpostsapplication.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.view.posts.PostsAdapter

@BindingAdapter("data")
fun setPostList(recyclerView: RecyclerView, data: List<Post>?) {
    if (recyclerView.adapter is PostsAdapter) {
            (recyclerView.adapter as PostsAdapter).setPostList(data)
    }
}
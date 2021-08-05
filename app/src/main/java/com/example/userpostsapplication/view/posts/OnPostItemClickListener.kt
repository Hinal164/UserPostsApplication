package com.example.userpostsapplication.view.posts

import com.example.userpostsapplication.data.model.Post

interface OnPostItemClickListener {
    fun onPostItemClick(post: Post)
}
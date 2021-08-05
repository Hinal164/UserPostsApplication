package com.example.userpostsapplication.view.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.databinding.ItemPostBinding

class PostsAdapter(private var postList: List<Post>, private val listener: OnPostItemClickListener) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position],listener)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setPostList(postList: List<Post>?) {
        if (postList != null) {
            this.postList = postList
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(private val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root) {

        fun bind(post: Post, listener: OnPostItemClickListener) {
            itemPostBinding.post=post
            itemPostBinding.listener=listener
            itemPostBinding.executePendingBindings()
        }
    }
}
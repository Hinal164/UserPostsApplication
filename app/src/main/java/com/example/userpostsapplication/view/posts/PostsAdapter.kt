package com.example.userpostsapplication.view.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.databinding.ItemPostBinding

abstract class PostsAdapter(private var postList: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
        holder.itemPostBinding.root.setOnClickListener { onPostClick(postList[position]) }
    }

    abstract fun onPostClick(post: Post)

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setPostList(postList: List<Post>?) {
        if (postList != null) {
            this.postList = postList
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root) {

        fun bind(post: Post) {
            itemPostBinding.tvUserIdValue.text = post.userId.toString()
            itemPostBinding.tvTitleValue.text = post.title
        }
    }
}
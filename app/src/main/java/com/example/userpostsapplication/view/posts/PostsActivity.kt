package com.example.userpostsapplication.view.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.databinding.ActivityPostsBinding
import com.example.userpostsapplication.utils.Status
import com.example.userpostsapplication.view.postdetail.PostDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : AppCompatActivity(), OnPostItemClickListener {

    private val postsViewModel: PostsViewModel by viewModels()
    private lateinit var binding: ActivityPostsBinding
    private lateinit var adapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel=postsViewModel
        binding.lifecycleOwner = this
        postsViewModel.setPostBoolean(true)
        setUpUI()
        setUpObserver()

    }

    private fun setUpUI() {
        binding.postRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter =  PostsAdapter(arrayListOf(),this)
        binding.postRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        postsViewModel.postListLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("TAG", "size: ${it.data?.size}")
                    binding.progressbar.visibility=View.GONE

                }
                Status.ERROR -> {
                    binding.progressbar.visibility=View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progressbar.visibility=View.VISIBLE
                }
            }
        })
    }

    override fun onPostItemClick(post: Post) {
        val intent = Intent(this@PostsActivity,PostDetailActivity::class.java)
        intent.putExtra("post",post as Parcelable)
        startActivity(intent)
    }
}
package com.example.userpostsapplication.view.postdetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.databinding.ActivityPostDetailBinding
import com.example.userpostsapplication.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {
    private val postDetailViewModel: PostDetailViewModel by viewModels()
    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        post = intent.getParcelableExtra("post")!!
        binding.tvTitleValue.text=post.title
        binding.tvBodyValue.text=post.body

        setUserObserver(post.userId)
        setCommentsObserver(post.id)

    }


    private fun setUserObserver(userId: Int) {
        postDetailViewModel.getSpecificUser(userId).observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.visibility=View.GONE
                    if(it.data!=null){
                        binding.tvUserNameValue.text = it.data.username
                    }
                }
                Status.ERROR -> {
                    binding.progressbar.visibility=View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progressbar.visibility=View.VISIBLE
                }
            }
        })
    }

    private fun setCommentsObserver(postId: Int) {
        postDetailViewModel.getCommentList(postId).observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.visibility=View.GONE
                    if(it.data!!.isNotEmpty()){
                        var comments: String = ""
                        var commentNumber = 1
                        it.data.forEach { comment ->
                            comments += commentNumber.toString() + ". " + comment.name + "\n"
                            commentNumber++
                        }
                        binding.tvCommentsValue.text = comments
                    }

                }
                Status.ERROR -> {
                    binding.progressbar.visibility=View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progressbar.visibility=View.VISIBLE
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item);
        }
    }
}
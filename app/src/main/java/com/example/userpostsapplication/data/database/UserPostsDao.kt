package com.example.userpostsapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userpostsapplication.data.model.Comment
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.data.model.User

@Dao
interface UserPostsDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(postList: List<Post>)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE user.id LIKE :userId")
    fun getUser(userId:Int) : LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userList: List<User>)

    @Query("SELECT * FROM comment WHERE comment.postId LIKE :postId")
    fun getPostComments(postId:Int): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(commentList: List<Comment>)


}
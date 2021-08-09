package com.example.userpostsapplication.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.remote.PostApiInterface
import com.example.userpostsapplication.data.repository.PostsRepository
import com.example.userpostsapplication.getOrAwaitValue
import com.example.userpostsapplication.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.mockito.Mockito.*
import retrofit2.Response
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class PostsRepositoryTest {
    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userPostsDao: UserPostsDao
    private lateinit var postApiInterface: PostApiInterface
    private lateinit var postsRepository: PostsRepository

    @Before
    fun setUp() {
        userPostsDao = mock(UserPostsDao::class.java)
        postApiInterface = mock(PostApiInterface::class.java)
        postsRepository = PostsRepository(
            postApiInterface,
            userPostsDao,
            coroutinesRule.dispatcher
        )
    }

    @Test
    fun getPosts() = coroutinesRule.runBlockingTest {
        val response = Response.success(TestUtil.createPostList())
        `when`(postApiInterface.fetchPosts()).thenReturn(response)
        postsRepository.fetchPosts().getOrAwaitValue()
        verify(postApiInterface, times(1)).fetchPosts()
        verify(userPostsDao, times(1)).insertPosts(anyList())
    }

}
package com.example.userpostsapplication.postDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.remote.PostApiInterface
import com.example.userpostsapplication.data.repository.PostDetailRepository
import com.example.userpostsapplication.getOrAwaitValue
import com.example.userpostsapplication.utils.MainCoroutineRule
import com.example.userpostsapplication.utils.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class PostDetailRepositoryTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutinesRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: PostDetailRepository
    private lateinit var dao: UserPostsDao
    private lateinit var postApiInterface: PostApiInterface

    @Before
    fun setUp() {
        dao = mock(UserPostsDao::class.java)
        postApiInterface = mock(PostApiInterface::class.java)
        repository = PostDetailRepository(
            postApiInterface,
            dao,
            coroutinesRule.dispatcher
        )
    }


    @Test
    fun getUser()= coroutinesRule.runBlockingTest {

        val response = Response.success(listOf(TestUtil.createUser(),TestUtil.createUser()))
        `when`(postApiInterface.fetchUsers()).thenReturn(response)
        repository.fetchUsers(1).getOrAwaitValue()
        verify(postApiInterface, times(1)).fetchUsers()
        verify(dao, times(1)).insertUsers(anyList())

    }

    @Test
    fun getComments()= coroutinesRule.runBlockingTest {

        val response = Response.success(TestUtil.createCommentList())
        `when`(postApiInterface.fetchComments()).thenReturn(response)
        repository.fetchCommentList(1).getOrAwaitValue()
        verify(postApiInterface, times(1)).fetchComments()
        verify(dao, times(1)).insertComments(anyList())

    }
}
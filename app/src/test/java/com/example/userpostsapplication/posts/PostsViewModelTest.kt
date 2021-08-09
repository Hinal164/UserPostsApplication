package com.example.userpostsapplication.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.data.repository.PostsRepository
import com.example.userpostsapplication.observeForTesting
import com.example.userpostsapplication.utils.*
import com.example.userpostsapplication.view.posts.PostsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mock

import org.hamcrest.MatcherAssert.*
import org.hamcrest.core.Is.`is`


@ExperimentalCoroutinesApi
class PostsViewModelTest {

    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var postsRepository: PostsRepository
    private lateinit var postsViewModel: PostsViewModel
    private val postListLiveData = TestUtil.createPostListLivedata()

    @Mock
    lateinit var observer : Observer<Resource<List<Post>>>

    @Before
    fun beforeAll(){
        postsRepository = mock(PostsRepository::class.java)
        postsViewModel = PostsViewModel(postsRepository)
    }

    @Test
    fun fetch_post_list_livedata() = coroutinesRule.runBlockingTest{

        `when`(postsRepository.fetchPosts()).thenReturn(postListLiveData)
        postsViewModel.setPostBoolean(true)
        //1st way to test postListLiveData
        postsViewModel.postListLiveData.observeForTesting {
            assertThat(postsViewModel.postListLiveData.value?.data, `is`(TestUtil.createPostList()))
            assertThat(postsViewModel.postListLiveData.value?.data?.get(0)?.id, `is`(1))
            assertThat(postsViewModel.postListLiveData.value?.data?.get(1)?.id, `is`(2))
        }
        //2nd way to test postListLiveData
       /* postsViewModel.postListLiveData.observeForever(observer)
        verify(observer, times(0)).onChanged(Resource.loading())
        verify(observer, times(1)).onChanged(Resource.success(TestUtil.createPostList()))*/
    }


}
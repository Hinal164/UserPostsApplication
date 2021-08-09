package com.example.userpostsapplication.postDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.userpostsapplication.data.database.UserPostsDao
import com.example.userpostsapplication.data.model.*
import com.example.userpostsapplication.data.remote.PostApiInterface
import com.example.userpostsapplication.data.repository.PostDetailRepository
import com.example.userpostsapplication.observeForTesting
import com.example.userpostsapplication.utils.*
import com.example.userpostsapplication.view.postdetail.PostDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.*
import org.hamcrest.core.Is.`is`
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito.*


@ExperimentalCoroutinesApi
class PostDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineRule= MainCoroutineRule()

    private lateinit var repository: PostDetailRepository
    private lateinit var dao: UserPostsDao
    private lateinit var viewModel: PostDetailViewModel
    private lateinit var postApiInterface: PostApiInterface

    @Mock
    lateinit var observer: Observer<Resource<List<Comment>>>
    @Mock
    lateinit var userObserver: Observer<Resource<User>>



    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun initViewModel() {
        dao = mock(UserPostsDao::class.java)
        postApiInterface = mock(PostApiInterface::class.java)
        repository=mock(PostDetailRepository::class.java)
        viewModel= PostDetailViewModel(repository)
    }


    @Test
    fun test_user_livedata()=coroutineRule.runBlockingTest{

        `when`(repository.fetchUsers(1)).thenReturn(TestUtil.createUserLiveData())
        viewModel.setUserId(1)

        //1st way to test userLiveData
        viewModel.userLiveData.observeForTesting {
            assertThat(viewModel.userLiveData.value?.data, `is`(TestUtil.createUser()))
            assertThat(viewModel.userLiveData.value?.data?.username, `is`("Bert"))
        }

        //2nd way to test userLiveData
        /*viewmodel.userLiveData.observeForever(userObserver)
        verify(userObserver, times(0)).onChanged(Resource.loading())
        verify(userObserver, times(1)).onChanged(Resource.success(TestUtil.createUser()))*/
    }

    @Test
    fun test_comment_list_livedata()=coroutineRule.runBlockingTest{
        `when`(repository.fetchCommentList(1)).thenReturn(TestUtil.createCommentListLivedata())
        viewModel.setPostId(1)
        //1st way to test commentListLiveData
        viewModel.commentListLiveData.observeForTesting {
            assertThat(viewModel.commentListLiveData.value?.data, `is`(TestUtil.createCommentListLivedata().value?.data))
            assertThat(viewModel.commentListLiveData.value?.data?.get(0)?.id, `is`(1))
            assertThat(viewModel.commentListLiveData.value?.data?.get(1)?.id, `is`(2))
        }

        //2nd way to test commentListLiveData by below code
       /* viewmodel.commentListLiveData.observeForever(observer)
        verify(observer, times(0)).onChanged(Resource.loading())
        verify(observer, times(1)).onChanged(Resource.success(TestUtil.createCommentList()))*/

    }

}
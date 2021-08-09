package com.example.userpostsapplication.utils

import androidx.lifecycle.MutableLiveData
import com.example.userpostsapplication.data.model.*

object TestUtil {

    private val post1 = Post(
        1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
    )
    private val post2 = Post(
        1, 2, "qui est esse",
        "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"
    )
    fun createPostListLivedata() = MutableLiveData(
        Resource.success(listOf(post1, post2))
    )
    fun createPostList() = listOf(post1, post2)


    private val comment1=Comment(1,1,"id labore ex et quam laborum","Eliseo@gardner.biz","laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium")
    private val comment2=Comment(1,2,"quo vero reiciendis velit similique earum", "Jayne_Kuhic@sydney.com","est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et")
    fun createCommentListLivedata() = MutableLiveData(
        Resource.success(listOf(comment1, comment2))
    )
    fun createCommentList() = listOf(comment1, comment2)


    fun createUser() = User(
        id = 1,
        name = "Leanne Graham",
        username = "Bert",
        email = "Sincere@april.biz",
        address = Address(
            street = "Kulas Light",
            suite = "Apt. 556",
            city = "Gwenborough",
            zipcode = "92998-3874",
            geo = Geo("-37.3159", "81.1496")
        ),
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        company = Company(
            name = "Romaguera-Crona",
            catchPhrase = "Multi-layered client-server neural-net",
            bs = "harness real-time e-markets"
        )
    )
    fun createUserLiveData() =MutableLiveData(Resource.success(createUser()))
}
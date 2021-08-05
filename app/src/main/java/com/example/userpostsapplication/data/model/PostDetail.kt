package com.example.userpostsapplication.data.model

class PostDetail(){
    var postId:Int=0
    var userId:Int=0
    var userName:String?=""
    var commentList:List<Comment>? = emptyList()
}

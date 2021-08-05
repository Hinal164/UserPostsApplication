package com.example.userpostsapplication.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Post(
    val userId:Int,
    @PrimaryKey
    val id:Int,
    val title:String,
    val body:String
) : Parcelable

package com.example.userpostsapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.userpostsapplication.data.model.AddressTypeConverter
import com.example.userpostsapplication.data.model.CompanyTypeConverter
import com.example.userpostsapplication.data.model.Comment
import com.example.userpostsapplication.data.model.Post
import com.example.userpostsapplication.data.model.User

@Database(entities = [Post::class,User::class,Comment::class],version = 1,exportSchema = false)
@TypeConverters(CompanyTypeConverter::class, AddressTypeConverter::class)
abstract class UserPostsDatabase : RoomDatabase() {
    abstract fun userPostsDao(): UserPostsDao
}
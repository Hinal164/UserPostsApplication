package com.example.userpostsapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.userpostsapplication.data.CompanyTypeConverter

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String?,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company

)
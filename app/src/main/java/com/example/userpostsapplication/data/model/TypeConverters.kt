package com.example.userpostsapplication.data

import androidx.room.TypeConverter
import com.example.userpostsapplication.data.model.Address
import com.example.userpostsapplication.data.model.Company
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CompanyTypeConverter {
    var gson = Gson()
    @TypeConverter
    fun fromSource(source: Company): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(source: String): Company {
        return gson.fromJson(source, Company::class.java)

    }
}
object AddressTypeConverter {
    var gson = Gson()
    @TypeConverter
    fun fromSource(source: Address): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(source: String): Address {
        return gson.fromJson(source, Address::class.java)
    }
}
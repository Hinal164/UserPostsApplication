package com.example.userpostsapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

fun <T, A> performNetworkAndDatabaseOperation(
    databaseQuery: () -> LiveData<T> = { MutableLiveData() },
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit = { Unit },
    dispatcher: CoroutineDispatcher
): LiveData<Resource<T>> =
    liveData(dispatcher) {
        emit(Resource.loading(null))
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(responseStatus.message))
            emitSource(source)
        }
    }

suspend fun <T> transformResult(call: suspend () -> Response<T>): Resource<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Resource.success(body)
        }
        return error(" ${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return error(e.message ?: e.toString())
    }
}

fun <T> error(message: String): Resource<T> {
    return Resource.error("Network call has failed for a following reason: $message")
}





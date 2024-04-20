package com.nanaland.util.network

import retrofit2.HttpException
import retrofit2.Response
import java.text.SimpleDateFormat

interface NetworkResultHandler {
    suspend fun <T:Any> handleResult(
        responseFunction: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = responseFunction()
            if (response.isSuccessful) {
                if (response.body() == null) {
                    NetworkResult.Success(response.code(), null)
                } else {
                    NetworkResult.Success(response.code(), response.body())
                }
            } else {
                val message = ""
                NetworkResult.Error(response.code(), message)
            }
        } catch (e: HttpException) {
            val currentTime = System.currentTimeMillis()
            val format = SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
            NetworkResult.Error(e.code(), "")
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }
}

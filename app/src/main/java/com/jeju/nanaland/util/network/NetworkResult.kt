package com.jeju.nanaland.util.network

import com.jeju.nanaland.util.log.LogUtil
import java.util.Arrays

sealed class NetworkResult<out T> {
    data class Success<out T>(val code: Int, val message: String?, val data: T?) : NetworkResult<T>()
    data class Error(val code: Int, val message: String?) : NetworkResult<Nothing>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()

    val isSuccess get() = this is Success
    val isError get() = this is Error
    val isException get() = this is Exception
}

inline fun <T> NetworkResult<T>.onSuccess(action: (code: Int, message: String?, data: T?) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) {
        action(this.code, this.message, this.data)
    }
    return this
}

inline fun <T> NetworkResult<T>.onError(action: (code: Int, message: String?) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) {
        action(this.code, this.message)
        LogUtil.e("onError", "code: ${code}\nmessage: ${message}")
    }
    return this
}

inline fun <T> NetworkResult<T>.onException(action: (data: Throwable) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Exception) {
        action(this.e)
        LogUtil.e("onException", e.message + "\n" + Arrays.toString(e.stackTrace).replace(", ", "\n")
            .replace("[", "").replace("]", ""))
    }
    return this
}

inline fun <T> NetworkResult<T>.finally(action: (data: NetworkResult<T>) -> Unit): NetworkResult<T> {
    action(this)
    return this
}
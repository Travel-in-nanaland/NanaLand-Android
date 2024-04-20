package com.nanaland.util.network

sealed class NetworkResult<out T> {
    data class Success<out T>(val code: Int, val data: T?) : NetworkResult<T>()
    data class Error(val code: Int, val message: String?) : NetworkResult<Nothing>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()

    public val isSuccess get() = this is Success
    public val isError get() = this is Error
    public val isException get() = this is Exception
}

public inline fun <T> NetworkResult<T>.onSuccess(action: (data: NetworkResult.Success<T>) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) {
        action(this)
    }
    return this
}

public inline fun <T> NetworkResult<T>.onError(action: (data: NetworkResult.Error) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) {
        action(this)
    }
    return this
}

public inline fun <T> NetworkResult<T>.onException(action: (data: NetworkResult.Exception) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Exception) {
        action(this)
    }
    return this
}
package com.nanaland.util.network

import android.util.Log
import com.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : Interceptor {

    private val mutex = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {
        lateinit var response: Response
        runBlocking {
            mutex.withLock {
                val accessToken = getAccessTokenUseCase().first()
                if (accessToken.isNullOrEmpty()) {
                    response = errorResponse(chain.request())
                    return@runBlocking
                } else {
                    Log.e("TokenInterceptor", "${accessToken}")
                    val request = chain.request().newBuilder().header("Authorization", "Bearer $accessToken").build()
                    response = chain.proceed(request)
                }
            }
        }
        return response
    }
}
private fun errorResponse(request: Request): Response = Response.Builder()
    .request(request)
    .protocol(Protocol.HTTP_2)
    .code(499)
    .message("Sign in needed")
    .body(ResponseBody.create(null, ""))
    .build()
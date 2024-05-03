package com.nanaland.util.network

import android.util.Log
import com.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken: String = runBlocking {
            getAccessTokenUseCase().first()
        } ?: return errorResponse(chain.request())

        Log.e("TokenInterceptor", "${accessToken}")
        val request = chain.request().newBuilder().header("Authorization", "Bearer $accessToken").build()

        return chain.proceed(request)
    }
}
private fun errorResponse(request: Request): Response = Response.Builder()
    .request(request)
    .protocol(Protocol.HTTP_2)
    .code(499)
    .message("Sign in needed")
    .body(ResponseBody.create(null, ""))
    .build()
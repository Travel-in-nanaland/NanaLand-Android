package com.nanaland.util.network

import android.util.Log
import com.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.nanaland.util.log.LogUtil
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
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val reissueAccessTokenUseCase: ReissueAccessTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase
) : Interceptor {

    private val mutex = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {
        lateinit var response: Response
        runBlocking {
            mutex.withLock {
                val accessToken = getAccessTokenUseCase().first()
                if (accessToken.isNullOrEmpty()) {
                    response = errorResponse(chain.request())
                    LogUtil.e("Network Error", "Access Token is Null or Empty")
                    return@runBlocking
                } else {
                    Log.e("TokenInterceptor", "${accessToken}")
                    val request = chain.request().newBuilder().header("Authorization", "Bearer $accessToken").build()
                    response = chain.proceed(request)
                    if (response.code == 401) {
                        val refreshToken = getRefreshTokenUseCase().first()
                        if (refreshToken.isNullOrEmpty()) {
                            response = errorResponse(chain.request())
                            LogUtil.e("Network Error", "Refresh Token is Null or Empty")
                        } else {
                            reissueAccessTokenUseCase(refreshToken).first().onSuccess { code, data ->
                                val newAccessToken = data?.data?.accessToken ?: ""
                                val newRefreshToken = data?.data?.refreshToken ?: ""
                                saveRefreshTokenUseCase(newRefreshToken)
                                saveAccessTokenUseCase(newAccessToken)
                                val newRequest = chain.request().newBuilder().header("Authorization", "Bearer $newAccessToken").build()
                                response = chain.proceed(newRequest)
                            }.onError { code, message ->
                                response = errorResponse(chain.request())
                            }.onException {
                                response = errorResponse(chain.request())
                            }
                        }
                    }
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

private fun newRequestWithToken(token: String, request: Request): Request =
    request.newBuilder()
        .header("Authorization", "Bearer $token")
        .build()
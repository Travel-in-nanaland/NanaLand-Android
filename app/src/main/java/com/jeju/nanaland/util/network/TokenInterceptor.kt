package com.jeju.nanaland.util.network

import android.util.Log
import com.jeju.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.util.log.LogUtil
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
        return runBlocking<Response> {
            val request = chain.request()

            mutex.withLock {
                val accessToken = getAccessTokenUseCase().first()
                if (accessToken.isNullOrEmpty()) {
                    response = chain.proceed(request.newBuilder().build())
//                    response = errorResponse(request)
                    LogUtil.e("Network Error", "Access Token is Null or Empty")
                } else {
                    LogUtil.e("TokenInterceptor", "${accessToken}")
                    response = chain.proceed(request.newBuilder().header("Authorization", "Bearer $accessToken").build())
                    if (response.code == 401) {
                        response.close()
                        val refreshToken = getRefreshTokenUseCase().first()
                        if (refreshToken.isNullOrEmpty()) {
                            response = errorResponse(request)
                            LogUtil.e("Network Error", "Refresh Token is Null or Empty")
                        } else {
                            Log.d("Token", "get refreshToken " + refreshToken)
                            reissueAccessTokenUseCase(refreshToken).first().onSuccess { code, message, data ->
                                Log.d("Token", "code$code, message$message, data$data")
                                val newAccessToken = data?.accessToken ?: ""
                                val newRefreshToken = data?.refreshToken ?: ""
                                saveRefreshTokenUseCase(newRefreshToken)
                                saveAccessTokenUseCase(newAccessToken)
                                response = chain.proceed(request.newBuilder().header("Authorization", "Bearer $newAccessToken").build())
                            }.onError { code, message ->
                                Log.e("Reissue Token ERROR", "code $code, message $message")
                                response = errorResponse(request)
                            }.onException {
                                Log.e("Reissue Token ERROR", "exception $it")
                                response = errorResponse(request)
                            }
                        }
                    }
                }
                response
            }
        }
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
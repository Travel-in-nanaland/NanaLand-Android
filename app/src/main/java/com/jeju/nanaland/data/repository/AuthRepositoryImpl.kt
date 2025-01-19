package com.jeju.nanaland.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.jeju.nanaland.data.api.AuthApi
import com.jeju.nanaland.domain.entity.auth.AuthTokenData
import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository, NetworkResultHandler {

    // AccessToken 재발급
    override suspend fun reissueAccessToken(
        refreshToken: String
    ): NetworkResult<AuthTokenData> {
        return handleResult { authApi.reissueAccessToken(
            "Bearer $refreshToken",
            getFCMToken()
        ) }
    }

    // 로그인
    override suspend fun signIn(
        data: SignInRequest
    ): NetworkResult<AuthTokenData> {
        return handleResult { authApi.signIn(body = data) }
    }

    override suspend fun signUp(
        data: SignUpRequest,
        image: String?
    ): NetworkResult<AuthTokenData> {
        return handleResult { authApi.signUp(data,image) }
    }

    override suspend fun getFCMToken(): String? {
        return suspendCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                continuation.resume(
                    if (!task.isSuccessful) {
                        null
                    } else {
                        task.result
                    }
                )
            }
        }
    }

    override suspend fun duplicateNickname(
        data: String,
    ): NetworkResult<Unit> {
        return handleResult { authApi.duplicateNickname(data) }
    }
}
package com.jeju.nanaland.data.repository

import com.google.gson.GsonBuilder
import com.jeju.nanaland.data.api.AuthApi
import com.jeju.nanaland.domain.entity.auth.AuthTokenData
import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository, NetworkResultHandler {

    // AccessToken 재발급
    override suspend fun reissueAccessToken(
        refreshToken: String
    ): NetworkResult<AuthTokenData> {
        return handleResult { authApi.reissueAccessToken("Bearer $refreshToken") }
    }

    // 로그인
    override suspend fun signIn(
        data: SignInRequest
    ): NetworkResult<AuthTokenData> {
        return handleResult { authApi.signIn(body = data) }
    }

    override suspend fun signUp(
        data: SignUpRequest,
        image: File?
    ): NetworkResult<AuthTokenData> {
        val multipartImage: MultipartBody.Part? = image?.let {
            val imageBody = image.asRequestBody("image/png".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("multipartFile", imageBody.toString(), imageBody)
        }

        val gson = GsonBuilder().setLenient().setPrettyPrinting().create();
        val json = gson.toJson(data)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        return handleResult { authApi.signUp(requestBody, multipartImage) }
    }
}
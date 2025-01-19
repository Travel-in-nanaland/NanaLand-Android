package com.jeju.nanaland.data.api

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Url

interface FTPWithoutTokenApi {
    // Pre-Signed URL 로 업로드
    @PUT
    suspend fun put(
        @Url url: String,
        @Body files: RequestBody,
    ): Response<Unit>

}
package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.file.FileComplete
import com.jeju.nanaland.domain.entity.file.FileInitCommand
import com.jeju.nanaland.domain.entity.file.FileInitResponse
import com.jeju.nanaland.domain.response.ResponseWrapper
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Tag
import retrofit2.http.Url

interface FTPApi {
    // Pre-Signed URL 업로드 시작 API
    @POST("/file/upload-init")
    suspend fun init(
        @Body command: FileInitCommand
    ): ResponseWrapper<FileInitResponse>

    // Pre-Signed URL 로 업로드
    @PUT
    suspend fun put(
        @Url url: String,
        @Body files: RequestBody,
        @Tag withoutAuthHeader: String = "why kotlin Boolean to java boolean"
    ): Response<Unit>

    // Pre-Signed URL 업로드 완료 API
    @POST("/file/upload-complete")
    suspend fun complete(
        @Body complete: FileComplete
    ): Response<ResponseWrapper<Unit>>
}
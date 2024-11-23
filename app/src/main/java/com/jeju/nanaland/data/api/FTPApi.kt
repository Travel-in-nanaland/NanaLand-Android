package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.file.FileComplete
import com.jeju.nanaland.domain.entity.file.FileInitCommand
import com.jeju.nanaland.domain.entity.file.FileInitResponse
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FTPApi {
    // Pre-Signed URL 업로드 시작 API
    @POST("/file/upload-init")
    suspend fun init(
        @Body command: FileInitCommand
    ): ResponseWrapper<FileInitResponse>

    // Pre-Signed URL 업로드 완료 API
    @POST("/file/upload-complete")
    suspend fun complete(
        @Body complete: FileComplete
    ): Response<ResponseWrapper<Unit>>
}
package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.response.ResponseWrapper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ReportApi {

    // 정보 수정 제안
    @Multipart
    @POST("report/info-fix")
    suspend fun informationModificationProposal(
        @Part("reqDto") data: RequestBody,
        @Part images: List<MultipartBody.Part>?
    ): Response<ResponseWrapper<String>>

    // 리뷰 신고
    @Multipart
    @POST("report/claim")
    suspend fun reportReview(
        @Part("reqDto") data: RequestBody,
        @Part image: List<MultipartBody.Part>?
    ): Response<ResponseWrapper<String>>
}
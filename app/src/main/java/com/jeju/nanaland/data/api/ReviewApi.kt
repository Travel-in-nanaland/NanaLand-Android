package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.response.ResponseWrapper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApi {

    // 내가 쓴 리뷰 글 삭제
    @DELETE("review/my/{id}")
    suspend fun deleteReview(
        @Path("id") id: Int
    ): Response<ResponseWrapper<String>>

    // 마이페이지에서 내가 쓴 리뷰 글 상세 조회
    @GET("review/my/{id}")
    suspend fun getMyReviewList(
        @Path("id") id: Int
    ): Response<ResponseWrapper<MyReviewData>>

    // 리뷰 위한 게시글 검색 자동완성
    @GET("review/search/auto-complete")
    suspend fun getReviewAutoComplete(
        @Query("keyword") keyword: String,
    )

    // 리뷰 리스트 조회
    @GET("review/list/{id}")
    suspend fun getReviewList(
        @Path("id") id: Int,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<ReviewListData>>

    // 리뷰 생성
    @POST("review/{id}")
    suspend fun createReview(
        @Path("id") id: Int,
        @Query("category") category: String,
        @Part("createReviewDto") data: RequestBody,
        @Part image: MutableList<MultipartBody.Part?>
    ): Response<ResponseWrapper<String>>

    // 리뷰 좋아요 토글
    @POST("review/heart/{id}")
    suspend fun toggleReviewFavorite(
        @Path("id") id: Int
    ): Response<ResponseWrapper<ReviewFavorite>>
}
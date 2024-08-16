package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.review.MemberReviewDetail
import com.jeju.nanaland.domain.entity.review.MyReviewData
import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewKeywordResult
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.entity.review.ReviewThumbnailData
import com.jeju.nanaland.domain.response.ResponsePagingWrapper
import com.jeju.nanaland.domain.response.ResponseWrapper
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
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
    suspend fun getReviewAutoCompleteKeyword(
        @Query("keyword") keyword: String,
    ): Response<ResponseWrapper<List<ReviewKeywordResult>>>

    // 회원별 리뷰 썸네일 리스트 조회(6~12개)
    @GET("review/preview")
    suspend fun getReviewThumbnailListByUser(
        @Query("memberId") memberId: Int,
    ): Response<ResponseWrapper<ReviewThumbnailData>>

    // 게시물 별 리뷰 리스트 조회
    @GET("review/list/{id}")
    suspend fun getReviewListByPost(
        @Path("id") id: Int,
        @Query("category") category: ReviewCategoryType,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<ReviewListData>>

    // 회원별 리뷰 리스트 조회
    @GET("review/list")
    suspend fun getReviewListByUser(
        @Query("memberId") id: Int?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<ResponsePagingWrapper<MemberReviewDetail>>>

    // 리뷰 생성
    @Multipart
    @POST("review/{id}")
    suspend fun createReview(
        @Path("id") id: Int,
        @Query("category") category: ReviewCategoryType,
        @Part("createReviewDto") data: RequestBody,
        @Part images: List<MultipartBody.Part>?
    ): Response<ResponseWrapper<String?>>

    // 리뷰 좋아요 토글
    @POST("review/heart/{id}")
    suspend fun toggleReviewFavorite(
        @Path("id") id: Int
    ): Response<ResponseWrapper<ReviewFavorite>>

    // 내가 쓴 리뷰 수정
    @Multipart
    @PUT("review/my/{id}")
    suspend fun modifyUserReview(
        @Path("id") id: Int,
        @Part("editReviewDto") data: RequestBody,
        @Part newImages: List<MultipartBody.Part>?
    ): Response<ResponseWrapper<String>>
}
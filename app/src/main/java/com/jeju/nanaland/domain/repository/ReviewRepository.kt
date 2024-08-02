package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.util.network.NetworkResult

interface ReviewRepository {

    // 리뷰 리스트 조회
    suspend fun getReviewList(
        id: Int,
        category: ReviewCategoryType,
        page: Int,
        size: Int,
    ): NetworkResult<ReviewListData>

    // 리뷰 생성
    suspend fun createReview(
        id: Int,
        category: ReviewCategoryType,
        images: List<UriRequestBody>,
        data: CreateReviewRequest
    ): NetworkResult<String?>

    // 리뷰 좋아요 토글
    suspend fun toggleReviewFavorite(
        id: Int
    ): NetworkResult<ReviewFavorite>

}
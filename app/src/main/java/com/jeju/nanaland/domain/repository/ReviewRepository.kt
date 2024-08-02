package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.review.MyReviewData
import com.jeju.nanaland.domain.entity.review.ReviewDataByUser
import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewKeywordResult
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.entity.review.ReviewThumbnailData
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import com.jeju.nanaland.domain.request.review.GetMyReviewListRequest
import com.jeju.nanaland.domain.request.review.GetReviewAutoCompleteKeywordRequest
import com.jeju.nanaland.domain.request.review.GetReviewByUserRequest
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.review.GetReviewThumbnailListByUserRequest
import com.jeju.nanaland.domain.request.review.ModifyUserReviewRequest
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.util.network.NetworkResult

interface ReviewRepository {

    // 내가 쓴 리뷰 글 삭제
    suspend fun deleteReview(
        data: DeleteReviewRequest
    ): NetworkResult<String>

    // 마이페이지에서 내가 쓴 리뷰 글 상세 조회
    suspend fun getMyReviewList(
        data: GetMyReviewListRequest
    ): NetworkResult<MyReviewData>

    // 리뷰 위한 게시글 검색 자동완성
    suspend fun getReviewAutoCompleteKeyword(
        data: GetReviewAutoCompleteKeywordRequest
    ): NetworkResult<ReviewKeywordResult>

    // 회원 별 리뷰 썸네일 리스트 조회(6~12개)
    suspend fun getReviewThumbnailListByUser(
        data: GetReviewThumbnailListByUserRequest
    ): NetworkResult<ReviewThumbnailData>

    // 회원 별 리뷰 리스트 조회
    suspend fun getReviewByUser(
        data: GetReviewByUserRequest
    ): NetworkResult<ReviewDataByUser>

    // 리뷰 리스트 조회
    suspend fun getReviewListByPost(
        data: GetReviewListByPostRequest
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

    // 내가 쓴 리뷰 수정
    suspend fun modifyUserReview(
        data: ModifyUserReviewRequest
    ): NetworkResult<String>
}
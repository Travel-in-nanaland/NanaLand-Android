package com.jeju.nanaland.data.repository

import androidx.paging.PagingSource
import com.google.gson.Gson
import com.jeju.nanaland.data.api.ReviewApi
import com.jeju.nanaland.domain.entity.review.MemberReviewDetail
import com.jeju.nanaland.domain.entity.review.MyReviewData
import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewKeywordResult
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.entity.review.ReviewThumbnailData
import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import com.jeju.nanaland.domain.request.review.GetMyReviewListRequest
import com.jeju.nanaland.domain.request.review.GetReviewAutoCompleteKeywordRequest
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.review.GetReviewThumbnailListByUserRequest
import com.jeju.nanaland.domain.request.review.ModifyUserReviewRequest
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class ReviewRepositoryImpl(
    private val api: ReviewApi
): ReviewRepository, NetworkResultHandler {

    // 내가 쓴 리뷰 글 삭제
    override suspend fun deleteReview(
        data: DeleteReviewRequest
    ): NetworkResult<String> {
        return handleResult {
            api.deleteReview(
                id = data.id
            )
        }
    }

    // 마이페이지에서 내가 쓴 리뷰 글 상세 조회
    override suspend fun getMyReviewList(
        data: GetMyReviewListRequest
    ): NetworkResult<MyReviewData> {
        return handleResult {
            api.getMyReviewList(
                id = data.id
            )
        }
    }

    // 리뷰 위한 게시글 검색 자동완성
    override suspend fun getReviewAutoCompleteKeyword(
        data: GetReviewAutoCompleteKeywordRequest
    ): NetworkResult<ReviewKeywordResult> {
        return handleResult {
            api.getReviewAutoCompleteKeyword(
                keyword = data.keyword
            )
        }
    }

    // 회원 별 리뷰 썸네일 리스트 조회(6~12개)
    override suspend fun getReviewThumbnailListByUser(
        data: GetReviewThumbnailListByUserRequest
    ): NetworkResult<ReviewThumbnailData> {
        return handleResult {
            api.getReviewThumbnailListByUser(
                memberId = data.memberId
            )
        }
    }

    // 리뷰 리스트 조회
    override suspend fun getReviewListByPost(
        data: GetReviewListByPostRequest
    ): NetworkResult<ReviewListData> {
        return handleResult {
            api.getReviewListByPost(
                id = data.id,
                category = data.category,
                page = data.page,
                size = data.size,
            )
        }
    }

    // 회원별 리뷰 리스트 조회
    override fun getReviewListByUser(
        id: Int?,
    ): PagingSource<Int, MemberReviewDetail> {
        return handleResultPaging { page, size ->
            api.getReviewListByUser(
                id = id,
                page = page,
                size = size,
            )
        }
    }

    // 리뷰 생성
    override suspend fun createReview(
        id: Int,
        category: ReviewCategoryType,
        images: List<UriRequestBody>,
        data: CreateReviewRequest
    ): NetworkResult<String?> {
        return handleResult {
            val reqData = Gson().toJson(data).toRequestBody("application/json".toMediaTypeOrNull())

            api.createReview(
                id = id,
                category = category,
                data = reqData,
                images = images.ifEmpty { null }?.map {
                    it.toMultipartBody("multipartFileList")
                }
            )
        }
    }

    // 리뷰 좋아요 토글
    override suspend fun toggleReviewFavorite(
        data: ToggleReviewFavoriteRequest
    ): NetworkResult<ReviewFavorite> {
        return handleResult {
            api.toggleReviewFavorite(
                id = data.id
            )
        }
    }

    // 내가 쓴 리뷰 수정
    override suspend fun modifyUserReview(
        data: ModifyUserReviewRequest
    ): NetworkResult<String> {
        return handleResult {
            api.modifyUserReview(
                id = data.id
            )
        }
    }
}
package com.jeju.nanaland.data.repository

import com.google.gson.GsonBuilder
import com.jeju.nanaland.data.api.ReviewApi
import com.jeju.nanaland.domain.entity.review.MyReviewData
import com.jeju.nanaland.domain.entity.review.ReviewDataByUser
import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewKeywordResult
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.entity.review.ReviewThumbnailData
import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import com.jeju.nanaland.domain.request.review.GetMyReviewListRequest
import com.jeju.nanaland.domain.request.review.GetReviewAutoCompleteKeywordRequest
import com.jeju.nanaland.domain.request.review.GetReviewByUserRequest
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.review.GetReviewThumbnailListByUserRequest
import com.jeju.nanaland.domain.request.review.ModifyUserReviewRequest
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ReviewRepositoryImpl(
    private val reviewApi: ReviewApi
) : ReviewRepository, NetworkResultHandler {

    // 내가 쓴 리뷰 글 삭제
    override suspend fun deleteReview(
        data: DeleteReviewRequest
    ): NetworkResult<String> {
        return handleResult {
            reviewApi.deleteReview(
                id = data.id
            )
        }
    }

    // 마이페이지에서 내가 쓴 리뷰 글 상세 조회
    override suspend fun getMyReviewList(
        data: GetMyReviewListRequest
    ): NetworkResult<MyReviewData> {
        return handleResult {
            reviewApi.getMyReviewList(
                id = data.id
            )
        }
    }

    // 리뷰 위한 게시글 검색 자동완성
    override suspend fun getReviewAutoCompleteKeyword(
        data: GetReviewAutoCompleteKeywordRequest
    ): NetworkResult<ReviewKeywordResult> {
        return handleResult {
            reviewApi.getReviewAutoCompleteKeyword(
                keyword = data.keyword
            )
        }
    }

    // 회원 별 리뷰 썸네일 리스트 조회(6~12개)
    override suspend fun getReviewThumbnailListByUser(
        data: GetReviewThumbnailListByUserRequest
    ): NetworkResult<ReviewThumbnailData> {
        return handleResult {
            reviewApi.getReviewThumbnailListByUser(
                memberId = data.memberId
            )
        }
    }

    // 회원 별 리뷰 리스트 조회
    override suspend fun getReviewByUser(
        data: GetReviewByUserRequest
    ): NetworkResult<ReviewDataByUser> {
        return handleResult {
            reviewApi.getReviewByUser(
                memberId = data.memberId,
                page = data.page,
                size = data.size
            )
        }
    }

    // 게시물 별 리뷰 리스트 조회
    override suspend fun getReviewListByPost(
        data: GetReviewListByPostRequest
    ): NetworkResult<ReviewListData> {
        return handleResult {
            reviewApi.getReviewListByPost(
                id = data.id,
                category = data.category,
                page = data.page,
                size = data.size
            )
        }
    }

    // 리뷰 생성
    override suspend fun createReview(
        data: CreateReviewRequest,
        images: List<File?>
    ): NetworkResult<String> {
        val multipartList = images.mapIndexed { idx, item ->
            val imageBody = item!!.asRequestBody("image/png".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("multipartFile", "${idx}.png", imageBody)
        }

        val gson = GsonBuilder().setLenient().setPrettyPrinting().create();
        val json = gson.toJson(data)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        return handleResult {
            reviewApi.createReview(
                id = data.id,
                category = data.category,
                data = requestBody,
                images = multipartList
            )
        }
    }

    // 리뷰 좋아요 토글
    override suspend fun toggleReviewFavorite(
        data: ToggleReviewFavoriteRequest
    ): NetworkResult<ReviewFavorite> {
        return handleResult {
            reviewApi.toggleReviewFavorite(
                id = data.id
            )
        }
    }

    // 내가 쓴 리뷰 수정
    override suspend fun modifyUserReview(
        data: ModifyUserReviewRequest
    ): NetworkResult<String> {
        return handleResult {
            reviewApi.modifyUserReview(
                id = data.id
            )
        }
    }
}
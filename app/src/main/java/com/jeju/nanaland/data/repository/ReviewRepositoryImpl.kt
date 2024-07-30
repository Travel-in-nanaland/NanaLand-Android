package com.jeju.nanaland.data.repository

import com.google.gson.Gson
import com.jeju.nanaland.data.api.ReviewApi
import com.jeju.nanaland.domain.entity.review.ReviewFavorite
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class ReviewRepositoryImpl(
    private val api: ReviewApi
): ReviewRepository, NetworkResultHandler {

    // 리뷰 리스트 조회
    override suspend fun getReviewList(
        id: Int,
        category: ReviewCategoryType,
        page: Int,
        size: Int,
    ): NetworkResult<ReviewListData> {
        return handleResult {
            api.getReviewList(
                id = id,
                category = category,
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
                    MultipartBody.Part.createFormData(
                        "imageList",null,it
                    )
                }
            )
        }
    }

    // 리뷰 좋아요 토글
    override suspend fun toggleReviewFavorite(
        id: Int
    ): NetworkResult<ReviewFavorite> {
        return handleResult {
            api.toggleReviewFavorite(
                id = id
            )
        }
    }

}
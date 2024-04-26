package com.nanaland.domain.repository

import com.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import com.nanaland.domain.request.nanapick.GetNanaPickListRequest
import com.nanaland.domain.response.nanapick.GetHomePreviewBannerResponse
import com.nanaland.domain.response.nanapick.GetNanaPickContentResponse
import com.nanaland.domain.response.nanapick.GetNanaPickListResponse
import com.nanaland.util.network.NetworkResult

interface NanaPickRepository {

    // 홈화면 미리보기 배너 4개
    suspend fun getHomePreviewBanner(
        accessToken: String
    ): NetworkResult<GetHomePreviewBannerResponse>

    // 나나 Pick 리스트
    suspend fun getNanaPickList(
        accessToken: String,
        data: GetNanaPickListRequest
    ): NetworkResult<GetNanaPickListResponse>

    // 나나 Pick 콘텐츠
    suspend fun getNanaPickContent(
        accessToken: String,
        data: GetNanaPickContentRequest
    ): NetworkResult<GetNanaPickContentResponse>
}
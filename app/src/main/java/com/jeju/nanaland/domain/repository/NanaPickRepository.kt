package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickListRequest
import com.jeju.nanaland.domain.response.nanapick.GetHomePreviewBannerResponse
import com.jeju.nanaland.domain.response.nanapick.GetNanaPickContentResponse
import com.jeju.nanaland.domain.response.nanapick.GetNanaPickListResponse
import com.jeju.nanaland.util.network.NetworkResult

interface NanaPickRepository {

    // 홈화면 미리보기 배너 4개
    suspend fun getHomePreviewBanner(): NetworkResult<GetHomePreviewBannerResponse>

    // 나나 Pick 리스트
    suspend fun getNanaPickList(
        data: GetNanaPickListRequest
    ): NetworkResult<GetNanaPickListResponse>

    // 나나 Pick 콘텐츠
    suspend fun getNanaPickContent(
        data: GetNanaPickContentRequest
    ): NetworkResult<GetNanaPickContentResponse>
}
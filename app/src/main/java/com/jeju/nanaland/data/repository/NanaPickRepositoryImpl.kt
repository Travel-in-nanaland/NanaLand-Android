package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.NanaPickApi
import com.jeju.nanaland.domain.repository.NanaPickRepository
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickListRequest
import com.jeju.nanaland.domain.response.nanapick.GetHomePreviewBannerResponse
import com.jeju.nanaland.domain.response.nanapick.GetNanaPickContentResponse
import com.jeju.nanaland.domain.response.nanapick.GetNanaPickListResponse
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class NanaPickRepositoryImpl(
    private val nanaPickApi: NanaPickApi
) : NanaPickRepository, NetworkResultHandler {

    // 홈화면 미리보기 배너 4개
    override suspend fun getHomePreviewBanner(): NetworkResult<GetHomePreviewBannerResponse> {
        return handleResult { nanaPickApi.getHomePreviewBanner() }
    }

    // 나나 Pick 리스트
    override suspend fun getNanaPickList(
        data: GetNanaPickListRequest
    ): NetworkResult<GetNanaPickListResponse> {
        return handleResult {
            nanaPickApi.getNanaPickList(
                page = data.page,
                size = data.size
            )
        }
    }

    // 나나 Pick 콘텐츠
    override suspend fun getNanaPickContent(
        data: GetNanaPickContentRequest
    ): NetworkResult<GetNanaPickContentResponse> {
        return handleResult {
            nanaPickApi.getNanaPickContent(
                id = data.id
            )
        }
    }
}
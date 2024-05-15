package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.nature.GetNatureContentRequest
import com.jeju.nanaland.domain.request.nature.GetNatureListRequest
import com.jeju.nanaland.domain.response.nature.GetNatureContentResponse
import com.jeju.nanaland.domain.response.nature.GetNatureListResponse
import com.jeju.nanaland.util.network.NetworkResult

interface NatureRepository {

    // 7대 자연 상세 정보 조회
    suspend fun getNatureContent(
        data: GetNatureContentRequest
    ): NetworkResult<GetNatureContentResponse>

    // 7대 자연 리스트 조회
    suspend fun getNatureList(
        data: GetNatureListRequest
    ): NetworkResult<GetNatureListResponse>
}
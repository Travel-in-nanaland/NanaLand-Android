package com.nanaland.domain.repository

import com.nanaland.domain.request.nature.GetNatureContentRequest
import com.nanaland.domain.request.nature.GetNatureListRequest
import com.nanaland.domain.response.nature.GetNatureContentResponse
import com.nanaland.domain.response.nature.GetNatureListResponse
import com.nanaland.util.network.NetworkResult

interface NatureRepository {

    // 7대 자연 상세 정보 조회
    suspend fun getNatureContent(
        accessToken: String,
        data: GetNatureContentRequest
    ): NetworkResult<GetNatureContentResponse>

    // 7대 자연 리스트 조회
    suspend fun getNatureList(
        accessToken: String,
        data: GetNatureListRequest
    ): NetworkResult<GetNatureListResponse>
}
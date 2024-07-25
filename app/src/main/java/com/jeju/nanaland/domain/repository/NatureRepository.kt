package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.nature.NatureContent
import com.jeju.nanaland.domain.entity.nature.NatureThumbnailListData
import com.jeju.nanaland.domain.request.nature.GetNatureContentRequest
import com.jeju.nanaland.domain.request.nature.GetNatureListRequest
import com.jeju.nanaland.util.network.NetworkResult

interface NatureRepository {

    // 7대 자연 상세 정보 조회
    suspend fun getNatureContent(
        data: GetNatureContentRequest
    ): NetworkResult<NatureContent>

    // 7대 자연 리스트 조회
    suspend fun getNatureList(
        data: GetNatureListRequest
    ): NetworkResult<NatureThumbnailListData>
}
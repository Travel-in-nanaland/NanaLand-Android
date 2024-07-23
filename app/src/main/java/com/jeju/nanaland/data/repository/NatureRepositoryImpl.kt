package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.NatureApi
import com.jeju.nanaland.domain.entity.nature.NatureContent
import com.jeju.nanaland.domain.entity.nature.NatureThumbnailListData
import com.jeju.nanaland.domain.repository.NatureRepository
import com.jeju.nanaland.domain.request.nature.GetNatureContentRequest
import com.jeju.nanaland.domain.request.nature.GetNatureListRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class NatureRepositoryImpl(
    private val natureApi: NatureApi
) : NatureRepository, NetworkResultHandler {

    // 7대 자연 상세 정보 조회
    override suspend fun getNatureContent(
        data: GetNatureContentRequest
    ): NetworkResult<NatureContent> {
        return handleResult {
            natureApi.getNatureContent(
                id = data.id,
                isSearch = data.isSearch
            )
        }
    }

    // 7대 자연 리스트 조회
    override suspend fun getNatureList(
        data: GetNatureListRequest
    ): NetworkResult<NatureThumbnailListData> {
        return handleResult {
            natureApi.getNatureList(
                addressFilterList = data.addressFilterList,
                page = data.page,
                size = data.size,
                keyword = null
            )
        }
    }
}
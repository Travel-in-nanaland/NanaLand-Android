package com.nanaland.data.repository

import com.nanaland.data.api.NatureApi
import com.nanaland.domain.repository.NatureRepository
import com.nanaland.domain.request.nature.GetNatureContentRequest
import com.nanaland.domain.request.nature.GetNatureListRequest
import com.nanaland.domain.response.nature.GetNatureContentResponse
import com.nanaland.domain.response.nature.GetNatureListResponse
import com.nanaland.util.network.NetworkResult
import com.nanaland.util.network.NetworkResultHandler

class NatureRepositoryImpl(
    private val natureApi: NatureApi
) : NatureRepository, NetworkResultHandler {

    // 7대 자연 상세 정보 조회
    override suspend fun getNatureContent(
        data: GetNatureContentRequest
    ): NetworkResult<GetNatureContentResponse> {
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
    ): NetworkResult<GetNatureListResponse> {
        return handleResult {
            natureApi.getNatureList(
                addressFilterList = data.addressFilterList,
                page = data.page,
                size = data.size
            )
        }
    }
}
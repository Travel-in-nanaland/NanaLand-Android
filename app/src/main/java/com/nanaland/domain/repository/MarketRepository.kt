package com.nanaland.domain.repository

import com.nanaland.domain.request.market.GetMarketContentRequest
import com.nanaland.domain.request.market.GetMarketListRequest
import com.nanaland.domain.response.market.GetMarketContentResponse
import com.nanaland.domain.response.market.GetMarketListResponse
import com.nanaland.util.network.NetworkResult

interface MarketRepository {

    // 전통시장 상세 정보 조회
    suspend fun getMarketContent(
        accessToken: String,
        data: GetMarketContentRequest
    ): NetworkResult<GetMarketContentResponse>

    // 전통시장 리스트 조회
    suspend fun getMarketList(
        accessToken: String,
        data: GetMarketListRequest
    ): NetworkResult<GetMarketListResponse>
}
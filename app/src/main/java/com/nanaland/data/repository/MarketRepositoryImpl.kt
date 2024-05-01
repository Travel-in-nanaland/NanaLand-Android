package com.nanaland.data.repository

import com.nanaland.data.api.MarketApi
import com.nanaland.domain.repository.MarketRepository
import com.nanaland.domain.request.market.GetMarketContentRequest
import com.nanaland.domain.request.market.GetMarketListRequest
import com.nanaland.domain.response.market.GetMarketContentResponse
import com.nanaland.domain.response.market.GetMarketListResponse
import com.nanaland.util.network.NetworkResult
import com.nanaland.util.network.NetworkResultHandler

class MarketRepositoryImpl(
    private val marketApi: MarketApi
) : MarketRepository, NetworkResultHandler {

    // 전통시장 상세 정보 조회
    override suspend fun getMarketContent(
        data: GetMarketContentRequest
    ): NetworkResult<GetMarketContentResponse> {
        return handleResult {
            marketApi.getMarketContent(
                id = data.id
            )
        }
    }

    // 전통시장 리스트 조회
    override suspend fun getMarketList(
        data: GetMarketListRequest
    ): NetworkResult<GetMarketListResponse> {
        return handleResult {
            marketApi.getMarketList(
                addressFilterList = data.addressFilterList,
                page = data.page,
                size = data.size
            )
        }
    }
}
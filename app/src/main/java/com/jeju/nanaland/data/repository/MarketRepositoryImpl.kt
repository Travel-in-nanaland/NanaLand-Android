package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.MarketApi
import com.jeju.nanaland.domain.entity.market.MarketContent
import com.jeju.nanaland.domain.repository.MarketRepository
import com.jeju.nanaland.domain.request.market.GetMarketContentRequest
import com.jeju.nanaland.domain.request.market.GetMarketListRequest
import com.jeju.nanaland.domain.entity.market.MarketThumbnailListData
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler


class MarketRepositoryImpl(
    private val marketApi: MarketApi
) : MarketRepository, NetworkResultHandler {

    // 전통시장 상세 정보 조회
    override suspend fun getMarketContent(
        data: GetMarketContentRequest
    ): NetworkResult<MarketContent> {
        return handleResult {
            marketApi.getMarketContent(
                id = data.id,
                isSearch = data.isSearch
            )
        }
    }

    // 전통시장 리스트 조회
    override suspend fun getMarketList(
        data: GetMarketListRequest
    ): NetworkResult<MarketThumbnailListData> {
        return handleResult {
            marketApi.getMarketList(
                addressFilterList = data.addressFilterList,
                page = data.page,
                size = data.size
            )
        }
    }
}
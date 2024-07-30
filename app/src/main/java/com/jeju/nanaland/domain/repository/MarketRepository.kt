package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.market.MarketContent
import com.jeju.nanaland.domain.request.market.GetMarketContentRequest
import com.jeju.nanaland.domain.request.market.GetMarketListRequest
import com.jeju.nanaland.domain.entity.market.MarketThumbnailListData
import com.jeju.nanaland.util.network.NetworkResult

interface MarketRepository {

    // 전통시장 상세 정보 조회
    suspend fun getMarketContent(
        data: GetMarketContentRequest
    ): NetworkResult<MarketContent>

    // 전통시장 리스트 조회
    suspend fun getMarketList(
        data: GetMarketListRequest
    ): NetworkResult<MarketThumbnailListData>
}
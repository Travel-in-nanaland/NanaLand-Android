package com.jeju.nanaland.domain.usecase.market

import com.jeju.nanaland.domain.repository.MarketRepository
import com.jeju.nanaland.domain.request.market.GetMarketListRequest
import kotlinx.coroutines.flow.flow

class GetMarketListUseCase(
    private val repository: MarketRepository
) {
    operator fun invoke(
        data: GetMarketListRequest
    ) = flow {
        val response = repository.getMarketList(data)
        emit(response)
    }
}
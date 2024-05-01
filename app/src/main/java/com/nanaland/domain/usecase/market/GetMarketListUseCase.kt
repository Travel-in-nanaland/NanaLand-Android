package com.nanaland.domain.usecase.market

import com.nanaland.domain.repository.MarketRepository
import com.nanaland.domain.request.market.GetMarketListRequest
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
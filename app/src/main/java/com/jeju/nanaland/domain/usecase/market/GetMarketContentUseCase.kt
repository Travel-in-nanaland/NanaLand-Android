package com.jeju.nanaland.domain.usecase.market

import com.jeju.nanaland.domain.repository.MarketRepository
import com.jeju.nanaland.domain.request.market.GetMarketContentRequest
import kotlinx.coroutines.flow.flow

class GetMarketContentUseCase(
    private val repository: MarketRepository
) {
    operator fun invoke(
        data: GetMarketContentRequest
    ) = flow {
        val response = repository.getMarketContent(data)
        emit(response)
    }
}
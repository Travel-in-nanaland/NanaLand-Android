package com.nanaland.domain.usecase.market

import com.nanaland.domain.repository.MarketRepository
import com.nanaland.domain.request.market.GetMarketContentRequest
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
package com.jeju.nanaland.domain.usecase.favorite

import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import kotlinx.coroutines.flow.flow

class GetFavoriteMarketListUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: GetFavoriteListRequest
    ) = flow {
        val response = repository.getFavoriteMarketList(data)
        emit(response)
    }
}
package com.jeju.nanaland.domain.usecase.favorite

import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import kotlinx.coroutines.flow.flow

class GetFavoriteExperienceListUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: GetFavoriteListRequest
    ) = flow {
        val response = repository.getFavoriteExperienceList(data)
        emit(response)
    }
}
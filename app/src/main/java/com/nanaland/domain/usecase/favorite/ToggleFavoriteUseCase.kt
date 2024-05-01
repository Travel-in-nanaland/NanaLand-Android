package com.nanaland.domain.usecase.favorite

import com.nanaland.domain.repository.FavoriteRepository
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import kotlinx.coroutines.flow.flow

class ToggleFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: ToggleFavoriteRequest
    ) = flow {
        val response = repository.toggleFavorite(data)
        emit(response)
    }
}
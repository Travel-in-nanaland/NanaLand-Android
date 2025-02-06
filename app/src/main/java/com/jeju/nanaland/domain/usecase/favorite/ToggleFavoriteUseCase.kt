package com.jeju.nanaland.domain.usecase.favorite

import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import kotlinx.coroutines.flow.flow

class ToggleFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: ToggleFavoriteRequest
    ) = flow {
        val d = if(data.category.uppercase() in listOf("ACTIVITY", "CULTURE_AND_ARTS"))
            data.copy(category = "EXPERIENCE")
        else data

        val response = repository.toggleFavorite(d)
        emit(response)
    }
}
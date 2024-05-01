package com.nanaland.domain.usecase.favorite

import com.nanaland.domain.repository.FavoriteRepository
import com.nanaland.domain.request.favorite.GetFavoriteListRequest
import kotlinx.coroutines.flow.flow

class GetAllFavoriteListUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: GetFavoriteListRequest
    ) = flow {
        val response = repository.getAllFavoriteList(data)
        emit(response)
    }
}
package com.jeju.nanaland.domain.usecase.restaurant

import com.jeju.nanaland.domain.repository.RestaurantRepository
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantContentRequest
import kotlinx.coroutines.flow.flow

class GetRestaurantContentUseCase(
    private val repository: RestaurantRepository
) {
    operator fun invoke(
        data: GetRestaurantContentRequest
    ) = flow {
        val response = repository.getRestaurantContent(data)
        emit(response)
    }
}
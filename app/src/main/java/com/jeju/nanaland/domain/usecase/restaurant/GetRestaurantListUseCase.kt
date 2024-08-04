package com.jeju.nanaland.domain.usecase.restaurant

import com.jeju.nanaland.domain.repository.RestaurantRepository
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantListRequest
import kotlinx.coroutines.flow.flow

class GetRestaurantListUseCase(
    private val repository: RestaurantRepository
) {
    operator fun invoke(
        data: GetRestaurantListRequest
    ) = flow {
        val response = repository.getRestaurantList(data)
        emit(response)
    }
}
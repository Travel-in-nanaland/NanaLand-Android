package com.jeju.nanaland.domain.usecase.favorite

import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import kotlinx.coroutines.flow.flow

class GetFavoriteExperienceListUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: GetFavoriteListRequest,
        type: SearchCategoryType
    ) = flow {
        val response = repository.getFavoriteExperienceList(data,
            if(type == SearchCategoryType.Activity) "ACTIVITY" else "CULTURE_AND_ARTS"
        )
        emit(response)
    }
}
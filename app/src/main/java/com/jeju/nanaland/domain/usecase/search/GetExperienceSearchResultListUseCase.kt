package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import kotlinx.coroutines.flow.flow

class GetExperienceSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest,
        type: SearchCategoryType
    ) = flow {
        val response = repository.getExperienceSearchResultList(
            data,
            if(type == SearchCategoryType.Activity) "ACTIVITY" else "CULTURE_AND_ARTS"
        )
        emit(response)
    }
}
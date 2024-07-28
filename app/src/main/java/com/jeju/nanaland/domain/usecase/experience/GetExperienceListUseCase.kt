package com.jeju.nanaland.domain.usecase.experience

import com.jeju.nanaland.domain.repository.ExperienceRepository
import com.jeju.nanaland.domain.request.experience.GetExperienceListRequest
import kotlinx.coroutines.flow.flow

class GetExperienceListUseCase(
    private val repository: ExperienceRepository
) {
    operator fun invoke(
        data: GetExperienceListRequest
    ) = flow {
        val response = repository.getExperienceList(data)
        emit(response)
    }
}
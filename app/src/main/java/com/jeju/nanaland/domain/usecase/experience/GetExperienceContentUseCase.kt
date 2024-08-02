package com.jeju.nanaland.domain.usecase.experience

import com.jeju.nanaland.domain.repository.ExperienceRepository
import com.jeju.nanaland.domain.request.experience.GetExperienceContentRequest
import kotlinx.coroutines.flow.flow

class GetExperienceContentUseCase(
    private val repository: ExperienceRepository
) {
    operator fun invoke(
        data: GetExperienceContentRequest
    ) = flow {
        val response = repository.getExperienceContent(data)
        emit(response)
    }
}
package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import kotlinx.coroutines.flow.flow

class UpdateUserProfileUseCase(
    val repository: MemberRepository
) {
    operator fun invoke(
        data: UpdateUserProfileRequest,
        image: UriRequestBody?
    ) = flow {
        val response = repository.updateUserProfile(data, image)
        emit(response)
    }
}
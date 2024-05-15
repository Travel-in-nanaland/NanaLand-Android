package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import kotlinx.coroutines.flow.flow

class UpdateUserTypeUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke(
        data: UpdateUserTypeRequest
    ) = flow {
        val response = repository.updateUserType(data)
        emit(response)
    }
}
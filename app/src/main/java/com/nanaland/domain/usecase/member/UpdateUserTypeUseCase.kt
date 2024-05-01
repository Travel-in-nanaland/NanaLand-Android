package com.nanaland.domain.usecase.member

import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.request.member.UpdateUserTypeRequest
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
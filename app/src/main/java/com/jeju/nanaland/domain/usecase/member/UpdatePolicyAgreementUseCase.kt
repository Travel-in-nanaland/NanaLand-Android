package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.member.UpdatePolicyAgreementRequest
import kotlinx.coroutines.flow.flow

class UpdatePolicyAgreementUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke(
        data: UpdatePolicyAgreementRequest
    ) = flow {
        val response = repository.updatePolicyAgreement(data)
        emit(response)
    }
}
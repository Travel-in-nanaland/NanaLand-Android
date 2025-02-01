package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import kotlinx.coroutines.flow.flow

class WithdrawUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke(
        data: WithdrawalRequest,
        isForce:Boolean
    ) = flow {
        val response = repository.withdraw(data, isForce)
        emit(response)
    }
}
package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import kotlinx.coroutines.flow.flow

class UpdateLanguageUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke(
        data: UpdateLanguageRequest
    ) = flow {
        val response = repository.updateLanguage(data)
        emit(response)
    }
}
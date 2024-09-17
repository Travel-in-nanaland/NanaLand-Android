package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.repository.MemberRepository
import javax.inject.Inject

class DuplicateNicknameUseCase @Inject constructor(
    val repository: AuthRepository
) {
    suspend operator fun invoke(
        data: String,
    ) = repository.duplicateNickname(data)
}
package com.jeju.nanaland.domain.usecase.auth

import com.jeju.nanaland.domain.repository.AuthRepository
import javax.inject.Inject

class GetFCMTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.getFCMToken()
}
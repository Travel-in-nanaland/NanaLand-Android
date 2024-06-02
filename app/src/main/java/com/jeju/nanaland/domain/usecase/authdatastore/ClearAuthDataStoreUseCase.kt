package com.jeju.nanaland.domain.usecase.authdatastore

import com.jeju.nanaland.domain.repository.AuthDataStoreRepository

class ClearAuthDataStoreUseCase(
    private val repository: AuthDataStoreRepository
) {
    suspend operator fun invoke() = repository.clearAll()
}
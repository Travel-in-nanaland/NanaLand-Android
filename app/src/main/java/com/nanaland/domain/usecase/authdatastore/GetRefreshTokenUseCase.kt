package com.nanaland.domain.usecase.authdatastore

import com.nanaland.domain.repository.AuthDataStoreRepository

class GetRefreshTokenUseCase(
    private val repository: AuthDataStoreRepository
) {
    suspend operator fun invoke() = repository.getRefreshToken()
}
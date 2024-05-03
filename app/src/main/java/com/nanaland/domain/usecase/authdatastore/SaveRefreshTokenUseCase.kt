package com.nanaland.domain.usecase.authdatastore

import com.nanaland.domain.repository.AuthDataStoreRepository

class SaveRefreshTokenUseCase(
    private val repository: AuthDataStoreRepository
) {
    suspend operator fun invoke(
        refreshToken: String
    ) {
        repository.saveRefreshToken(refreshToken)
    }
}
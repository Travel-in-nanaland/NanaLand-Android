package com.nanaland.domain.usecase.datastore

import com.nanaland.domain.repository.PreferencesDataStoreRepository

class SaveRefreshTokenUseCase(
    private val repository: PreferencesDataStoreRepository
) {
    suspend operator fun invoke(
        refreshToken: String
    ) {
        repository.saveRefreshToken(refreshToken)
    }
}
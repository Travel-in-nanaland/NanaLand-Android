package com.jeju.nanaland.domain.usecase.authdatastore

import com.jeju.nanaland.domain.repository.AuthDataStoreRepository

class SaveAccessTokenUseCase(
    private val repository: AuthDataStoreRepository
) {
    suspend operator fun invoke(
        accessToken: String
    ) {
        repository.saveAccessToken(accessToken)
    }
}
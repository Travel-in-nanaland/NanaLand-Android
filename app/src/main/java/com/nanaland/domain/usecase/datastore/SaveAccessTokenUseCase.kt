package com.nanaland.domain.usecase.datastore

import com.nanaland.domain.repository.PreferencesDataStoreRepository

class SaveAccessTokenUseCase(
    private val repository: PreferencesDataStoreRepository
) {
    suspend operator fun invoke(
        accessToken: String
    ) {
        repository.saveAccessToken(accessToken)
    }
}
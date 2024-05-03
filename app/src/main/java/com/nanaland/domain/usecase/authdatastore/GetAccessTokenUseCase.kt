package com.nanaland.domain.usecase.authdatastore

import com.nanaland.domain.repository.AuthDataStoreRepository

class GetAccessTokenUseCase(
    private val repository: AuthDataStoreRepository
) {
    suspend operator fun invoke() = repository.getAccessToken()
}
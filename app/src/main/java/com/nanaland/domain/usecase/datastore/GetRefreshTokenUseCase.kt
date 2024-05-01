package com.nanaland.domain.usecase.datastore

import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.repository.PreferencesDataStoreRepository

class GetRefreshTokenUseCase(
    private val repository: PreferencesDataStoreRepository
) {
    suspend operator fun invoke() = repository.getRefreshToken()
}
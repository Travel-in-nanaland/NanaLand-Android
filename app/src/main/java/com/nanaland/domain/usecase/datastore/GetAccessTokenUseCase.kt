package com.nanaland.domain.usecase.datastore

import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.repository.PreferencesDataStoreRepository
import kotlinx.coroutines.flow.flow

class GetAccessTokenUseCase(
    private val repository: PreferencesDataStoreRepository
) {
    suspend operator fun invoke() = repository.getAccessToken()
}
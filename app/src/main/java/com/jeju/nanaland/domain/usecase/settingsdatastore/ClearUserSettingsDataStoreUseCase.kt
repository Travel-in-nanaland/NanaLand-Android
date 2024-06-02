package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository

class ClearUserSettingsDataStoreUseCase(
    private val repository: UserSettingsDataStoreRepository
) {
    suspend operator fun invoke() = repository.clearAll()
}
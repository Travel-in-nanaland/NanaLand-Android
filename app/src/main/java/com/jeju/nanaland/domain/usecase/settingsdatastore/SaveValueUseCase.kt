package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository

class SaveValueUseCase(
    private val repository: UserSettingsDataStoreRepository
) {
    suspend operator fun invoke(
        key: String,
        value: String
    ) {
        repository.saveValue(key, value)
    }
}
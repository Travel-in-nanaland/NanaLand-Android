package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.SettingsDataStoreRepository

class GetLanguageUseCase(
    private val repository: SettingsDataStoreRepository
) {
    suspend operator fun invoke() = repository.getLanguage()
}
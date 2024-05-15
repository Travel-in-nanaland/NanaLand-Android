package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.SettingsDataStoreRepository

class SaveLanguageUseCase(
    private val repository: SettingsDataStoreRepository
) {
    suspend operator fun invoke(
        language: String
    ) {
        repository.saveLanguage(language)
    }
}
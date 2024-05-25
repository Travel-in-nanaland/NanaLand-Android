package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository

class SaveLanguageUseCase(
    private val repository: UserSettingsDataStoreRepository
) {
    suspend operator fun invoke(
        language: String
    ) {
        repository.saveLanguage(language)
    }
}
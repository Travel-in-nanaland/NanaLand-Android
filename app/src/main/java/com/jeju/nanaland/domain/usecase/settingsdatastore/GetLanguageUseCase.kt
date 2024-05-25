package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository

class GetLanguageUseCase(
    private val repository: UserSettingsDataStoreRepository
) {
    operator fun invoke() = repository.getLanguage()
}
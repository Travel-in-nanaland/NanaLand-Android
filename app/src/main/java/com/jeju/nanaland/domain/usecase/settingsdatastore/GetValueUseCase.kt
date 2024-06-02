package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository

class GetValueUseCase(
    private val repository: UserSettingsDataStoreRepository
) {
    operator fun invoke(
        key: String
    ) = repository.getValue(key)
}
package com.jeju.nanaland.domain.usecase.settingsdatastore

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.globalvalue.type.LanguageType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: UserSettingsDataStoreRepository
) {
    operator fun invoke() = repository.getValue(KEY_LANGUAGE).map {
            it?.let { LanguageType.codeToLanguage(it) }
    }

}
package com.jeju.nanaland.domain.usecase.settingsdatastore

import android.content.Context
import android.content.res.Configuration
import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.util.language.customContext
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(
    private val repository: UserSettingsDataStoreRepository,
    @ApplicationContext private val context: Context,
) {
    suspend operator fun invoke(
        value: LanguageType
    ) {
        val conf: Configuration = context.resources.configuration
        conf.setLocale(Locale(value.code))
        customContext = context.createConfigurationContext(conf)

        repository.saveValue(KEY_LANGUAGE, value.code)
    }
}
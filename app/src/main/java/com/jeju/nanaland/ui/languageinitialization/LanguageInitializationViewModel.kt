package com.jeju.nanaland.ui.languageinitialization

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.settingsdatastore.SaveValueUseCase
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.util.language.customContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class LanguageInitializationViewModel @Inject constructor(
    private val application: Application,
    private val saveValueUseCase: SaveValueUseCase
) : AndroidViewModel(application) {

    fun selectLanguage(language: String) {
        viewModelScope.launch { saveValueUseCase(
            key = KEY_LANGUAGE,
            value = language
        ) }
        val conf: Configuration = application.resources.configuration
        conf.setLocale(Locale(language))
        customContext = application.createConfigurationContext(conf)
    }
}
package com.jeju.nanaland.ui.languageselection

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.settingsdatastore.SaveLanguageUseCase
import com.jeju.nanaland.util.language.customContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val application: Application,
    private val saveLanguageUseCase: SaveLanguageUseCase
) : AndroidViewModel(application) {

    fun selectLanguage(language: String) {
        viewModelScope.launch { saveLanguageUseCase(language) }
        val conf: Configuration = application.resources.configuration
        conf.setLocale(Locale(language))
        customContext = application.createConfigurationContext(conf)
    }
}
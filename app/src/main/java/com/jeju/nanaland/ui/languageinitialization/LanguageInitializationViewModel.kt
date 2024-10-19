package com.jeju.nanaland.ui.languageinitialization

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.settingsdatastore.SetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.LanguageType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LanguageInitializationViewModel @Inject constructor(
    private val application: Application,
    private val setLanguageUseCase: SetLanguageUseCase
) : AndroidViewModel(application) {

    fun selectLanguage(language: LanguageType) {
        viewModelScope.launch {
            setLanguageUseCase(language)
        }
    }
}
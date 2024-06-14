package com.jeju.nanaland.ui.languagechange

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.usecase.member.UpdateLanguageUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetValueUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SaveValueUseCase
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.util.language.customContext
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageChangeViewModel @Inject constructor(
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val saveValueUseCase: SaveValueUseCase,
    private val getValueUseCase: GetValueUseCase,
    private val application: Application,
) : AndroidViewModel(application) {

    private val _currLanguage = MutableStateFlow("en")
    val currLanguage = _currLanguage.asStateFlow()

    fun updateLanguage(language: String) {
        val requestData = UpdateLanguageRequest(
            locale = language
        )
        updateLanguageUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    saveLanguage(language)
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "updateLanguage") }
            .launchIn(viewModelScope)
    }

    private fun saveLanguage(language: String) {
        val languageCode = when (language) {
            "ENGLISH" -> "en"
            "CHINESE" -> "zh"
            "MALAYSIA" -> "ms"
            "KOREAN" -> "ko"
            else -> "en"
        }
        viewModelScope.launch { saveValueUseCase(
            key = KEY_LANGUAGE,
            value = languageCode
        )
        val conf: Configuration = application.resources.configuration
        conf.setLocale(Locale(languageCode))
        customContext = application.createConfigurationContext(conf)}
    }

    init {
        getValueUseCase(key = KEY_LANGUAGE)
            .onEach { language ->
                _currLanguage.update { language ?: "en" }
            }
            .catch { LogUtil.e("flow Error", "getValueUseCase") }
            .launchIn(viewModelScope)
    }
}
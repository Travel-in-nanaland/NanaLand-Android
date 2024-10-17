package com.jeju.nanaland.ui.languagechange

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.usecase.member.UpdateLanguageUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.LanguageType
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
import javax.inject.Inject

@HiltViewModel
class LanguageChangeViewModel @Inject constructor(
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val application: Application,
) : AndroidViewModel(application) {

    private val _currLanguage = MutableStateFlow(LanguageType.English)
    val currLanguage = _currLanguage.asStateFlow()

    fun updateLanguage(language: LanguageType) {
        val requestData = UpdateLanguageRequest(
            locale = language
        )
        updateLanguageUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    setLanguageUseCase(language)
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "updateLanguage") }
            .launchIn(viewModelScope)
    }

    init {
        getLanguageUseCase()
            .onEach { language ->
                _currLanguage.update { language!! }
            }
            .catch { LogUtil.e("flow Error", "getValueUseCase") }
            .launchIn(viewModelScope)
    }
}
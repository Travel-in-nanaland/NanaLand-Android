package com.jeju.nanaland.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.authdatastore.ClearAuthDataStoreUseCase
import com.jeju.nanaland.domain.usecase.member.SignOutUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.ClearRecentSearchDataStoreUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.ClearUserSettingsDataStoreUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val clearAuthDataStoreUseCase: ClearAuthDataStoreUseCase,
    private val clearUserSettingsDataStoreUseCase: ClearUserSettingsDataStoreUseCase,
    private val clearRecentSearchDataStoreUseCase: ClearRecentSearchDataStoreUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : ViewModel() {

    fun signOut(
        moveToSignInScreen: () -> Unit
    ) {
        signOutUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    var locale:LanguageType
                    runBlocking {
                        locale = getLanguageUseCase().firstOrNull() ?: LanguageType.English
                    }
                    clearAuthDataStoreUseCase()
                    clearUserSettingsDataStoreUseCase()
                    clearRecentSearchDataStoreUseCase()
                    viewModelScope.launch { setLanguageUseCase(locale) }
                    moveToSignInScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "signOut") }
            .launchIn(viewModelScope)
    }
}
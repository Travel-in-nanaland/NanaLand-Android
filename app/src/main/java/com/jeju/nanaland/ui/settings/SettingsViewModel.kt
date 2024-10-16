package com.jeju.nanaland.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.authdatastore.ClearAuthDataStoreUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.member.SignOutUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.ClearRecentSearchDataStoreUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.ClearUserSettingsDataStoreUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetValueUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SaveValueUseCase
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val clearAuthDataStoreUseCase: ClearAuthDataStoreUseCase,
    private val clearUserSettingsDataStoreUseCase: ClearUserSettingsDataStoreUseCase,
    private val clearRecentSearchDataStoreUseCase: ClearRecentSearchDataStoreUseCase,
    private val getValueUseCase: GetValueUseCase,
    private val saveValueUseCase: SaveValueUseCase
) : ViewModel() {

    fun signOut(
        moveToSignInScreen: () -> Unit
    ) {
        signOutUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    var locale = "en"
                    getValueUseCase(key = KEY_LANGUAGE)
                        .onEach {
                            locale = it ?: "en"
                        }
                        .catch { LogUtil.e("flow Error", "getValueUseCase") }
                        .launchIn(viewModelScope)
                    clearAuthDataStoreUseCase()
                    clearUserSettingsDataStoreUseCase()
                    clearRecentSearchDataStoreUseCase()
                    viewModelScope.launch { saveValueUseCase(
                        key = KEY_LANGUAGE,
                        value = locale
                    ) }
                    moveToSignInScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "signOut") }
            .launchIn(viewModelScope)
    }
}
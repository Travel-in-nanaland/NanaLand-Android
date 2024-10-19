package com.jeju.nanaland.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.globalvalue.type.SplashCheckingState
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.util.intent.DeepLinkData
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.NetworkManager
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val reissueAccessTokenUseCase: ReissueAccessTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
    private val application: Application,
) : AndroidViewModel(application) {

    private val _checkingState = MutableStateFlow(SplashCheckingState.Network)
    val checkingState = _checkingState.asStateFlow()
    private val _isNetworkConnected = MutableStateFlow(false)
    val isNetworkConnected = _isNetworkConnected.asStateFlow()
    private val _isNetworkConnectionDialogShowing = MutableStateFlow(false)
    val isNetworkConnectionDialogShowing = _isNetworkConnectionDialogShowing.asStateFlow()

    fun checkNetworkState() {
        viewModelScope.launch {
            _isNetworkConnectionDialogShowing.value = false
            delay(1000)
            _isNetworkConnected.update { networkManager.isNetworkConnected }
            if (networkManager.isNetworkConnected) {
                _checkingState.update { SplashCheckingState.Language }
            } else {
                _isNetworkConnectionDialogShowing.value = true
            }
        }
    }

    fun checkLanguageState(
        deepLinkData: DeepLinkData,
        moveToLanguageInitScreen: () -> Unit
    ) {
        if(deepLinkData.language != null) {
            viewModelScope.launch {
                setLanguageUseCase(LanguageType.codeToLanguage(deepLinkData.language!!))
                _checkingState.update { SplashCheckingState.Authorization }
            }
        } else {
            getLanguageUseCase().onEach {
                if(it == null)
                    moveToLanguageInitScreen()
                else {
                    setLanguageUseCase(it)
                    _checkingState.update { SplashCheckingState.Authorization }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun checkSignInState(
        moveToMainScreen: () -> Unit,
        moveToSignInScreen: () -> Unit,
    ) {
        viewModelScope.launch {
            val refreshToken = getRefreshTokenUseCase().first()
            if (refreshToken.isNullOrEmpty()) {
                moveToSignInScreen()
            } else {
                reissueAccessTokenUseCase(refreshToken)
                    .onEach { networkResult ->
                        networkResult.onSuccess { code, message, data ->
                            data?.let {
                                saveAccessTokenUseCase(data.accessToken ?: "")
                                saveRefreshTokenUseCase(data.refreshToken ?: "")
                                getUserData()
                            }
                            moveToMainScreen()
                        }.onError { code, message ->
                            when (code) {
                                in 400 .. 499 -> {
                                    moveToSignInScreen()
                                }
                                else -> {

                                }
                            }
                        }.onException {

                        }
                    }
                    .catch { LogUtil.e("flow Error", "reissueAccessTokenUseCase") }
                    .launchIn(viewModelScope)
            }
        }
    }

    private fun getUserData() {
        getUserProfileUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        UserData.provider = data.provider ?: "GUEST"
                        if (UserData.provider == "GUEST") {
                            UserData.nickname = "GUEST"
                        } else {
                            UserData.nickname = data.nickname ?: "GUEST"
                        }
                    }
                }.onError { code, message ->

                }.onException {  }
            }
            .catch { LogUtil.e("flow error", "getUserProfileUseCase") }
            .launchIn(viewModelScope)
    }
}
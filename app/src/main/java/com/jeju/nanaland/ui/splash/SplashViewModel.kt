package com.jeju.nanaland.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.SplashCheckingState
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.NetworkManager
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase
) : ViewModel() {

    private val _checkingState = MutableStateFlow(SplashCheckingState.Network)
    val checkingState = _checkingState.asStateFlow()
    private val _isNetworkConnected = MutableStateFlow(false)
    val isNetworkConnected = _isNetworkConnected.asStateFlow()

    fun updateCheckingState(state: SplashCheckingState) {
        _checkingState.update { state }
    }

    fun checkNetworkState() {
        _isNetworkConnected.update { networkManager.isNetworkConnected }
        if (networkManager.isNetworkConnected) {
            _checkingState.update { SplashCheckingState.Language }
        }
    }

    fun checkLanguageState(
        moveToLanguageSelectionScreen: () -> Unit
    ) {
        getLanguageUseCase()
            .onEach {
                if (it.isNullOrEmpty()) {
                    moveToLanguageSelectionScreen()
                } else {
                    _checkingState.update { SplashCheckingState.Authorization }
                }
            }
            .catch { LogUtil.e("flow Error", "checkLanguageState") }
            .launchIn(viewModelScope)
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
                        networkResult.onSuccess { code, data ->
                            data?.let {
                                saveAccessTokenUseCase(data.data.accessToken ?: "")
                                saveRefreshTokenUseCase(data.data.refreshToken ?: "")
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
}
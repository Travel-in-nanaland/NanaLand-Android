package com.jeju.nanaland.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.usecase.auth.SignInUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
) : ViewModel() {

    fun signIn(
        provider: String,
        id: String,
        moveToMainScreen: () -> Unit,
        moveToSignUpScreen: () -> Unit,
    ) {
        var locale = "ENGLISH"
        getLanguageUseCase()
            .onEach {
                locale = when (it) {
                    "en" -> "ENGLISH"
                    "zh" -> "CHINESE"
                    "ms" -> "MALAYSIA"
                    "ko" -> "KOREAN"
                    else -> "ENGLISH"
                }
            }
            .catch { LogUtil.e("flow error", "getLanguageUseCase") }
            .launchIn(viewModelScope)
        val requestData = SignInRequest(
            locale = locale,
            provider = provider,
            providerId = id
        )
        signInUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        saveAccessTokenUseCase(data.data.accessToken ?: "")
                        saveRefreshTokenUseCase(data.data.refreshToken ?: "")
                        moveToMainScreen()
                    }
                }.onError { code, message ->
                    when (code) {
                        404 -> {
                            moveToSignUpScreen()
                        }
                    }
                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "signInUseCase") }
            .launchIn(viewModelScope)
    }
}
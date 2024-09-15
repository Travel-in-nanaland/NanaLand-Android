package com.jeju.nanaland.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.domain.usecase.auth.GetFCMTokenUseCase
import com.jeju.nanaland.domain.usecase.auth.SignInUseCase
import com.jeju.nanaland.domain.usecase.auth.SignUpUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetValueUseCase
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.globalvalue.userdata.UserData
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
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getValueUseCase: GetValueUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getFCMTokenUseCase: GetFCMTokenUseCase,
) : ViewModel() {

    fun signIn(
        provider: String,
        id: String,
        moveToMainScreen: () -> Unit,
        moveToSignUpScreen: () -> Unit,
    ) = viewModelScope.launch {
        var locale = "ENGLISH"
        getValueUseCase(key = KEY_LANGUAGE)
            .onEach {
                LogUtil.e("", "${it}")
                locale = when (it) {
                    "en" -> "ENGLISH"
                    "zh" -> "CHINESE"
                    "ms" -> "MALAYSIA"
                    "ko" -> "KOREAN"
                    else -> "ENGLISH"
                }
                val requestData = SignInRequest(
                    locale = locale,
                    provider = provider,
                    providerId = id,
                    fcmToken = getFCMTokenUseCase()
                )
                signInUseCase(requestData)
                    .onEach { networkResult ->
                        networkResult.onSuccess { code, message, data ->
                            data?.let {
                                saveAccessTokenUseCase(data.accessToken ?: "")
                                saveRefreshTokenUseCase(data.refreshToken ?: "")
                                getUserData()
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
            .catch { LogUtil.e("flow error", "getValueUseCase") }
            .launchIn(viewModelScope)
    }

    private fun getUserData() {
        getUserProfileUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        UserData.provider = data.provider ?: "GUEST"
                        UserData.nickname = data.nickname ?: "GUEST"
                    }
                }.onError { code, message ->

                }.onException {  }
            }
            .catch { LogUtil.e("flow error", "getUserProfileUseCase") }
            .launchIn(viewModelScope)
    }

    fun nonMemberSignUp(
        androidId: String,
        moveToMainScreen: () -> Unit,
    ) {
        var locale = "ENGLISH"
        getValueUseCase(key = KEY_LANGUAGE)
            .onEach {
                locale = when (it) {
                    "en" -> "ENGLISH"
                    "zh" -> "CHINESE"
                    "ms" -> "MALAYSIA"
                    "ko" -> "KOREAN"
                    else -> "ENGLISH"
                }
            }
            .catch { LogUtil.e("flow Error", "getValueUseCase") }
            .launchIn(viewModelScope)

        val requestData = SignUpRequest(
            consentItems = null,
            email = "GUEST@nanaland.com",
            provider = "GUEST",
            providerId = androidId,
            locale = locale,
            nickname = "GUEST"
        )

        signUpUseCase(requestData, null)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        UserData.nickname = "GUEST"
                        UserData.provider = "GUEST"
                        saveAccessTokenUseCase(data.accessToken ?: "")
                        saveRefreshTokenUseCase(data.refreshToken ?: "")
                        moveToMainScreen()
                    }
                }.onError { code, message ->
                    when (code) {
                        409 -> {
                            nonMemberSignIn(
                                locale = locale,
                                androidId = androidId,
                                moveToMainScreen = moveToMainScreen
                            )
                        }
                    }
                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "signUpUseCase") }
            .launchIn(viewModelScope)
    }

    private suspend fun nonMemberSignIn(
        locale: String,
        androidId: String,
        moveToMainScreen: () -> Unit,
    ) {
        val signInData = SignInRequest(
            locale = locale,
            provider = "GUEST",
            providerId = androidId,
            fcmToken = getFCMTokenUseCase()
        )
        signInUseCase(signInData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        UserData.nickname = "GUEST"
                        UserData.provider = "GUEST"
                        saveAccessTokenUseCase(data.accessToken ?: "")
                        saveRefreshTokenUseCase(data.refreshToken ?: "")
                        moveToMainScreen()
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "signInUseCase") }
            .launchIn(viewModelScope)
    }
}
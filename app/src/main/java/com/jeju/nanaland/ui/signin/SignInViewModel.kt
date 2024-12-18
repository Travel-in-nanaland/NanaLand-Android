package com.jeju.nanaland.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.domain.usecase.auth.GetFCMTokenUseCase
import com.jeju.nanaland.domain.usecase.auth.SignInUseCase
import com.jeju.nanaland.domain.usecase.auth.SignUpUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.globalvalue.userdata.UserData
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
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
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
        var locale:LanguageType
        runBlocking {
            locale = getLanguageUseCase().firstOrNull() ?: LanguageType.English
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
        var locale:LanguageType
        runBlocking {
            locale = getLanguageUseCase().firstOrNull() ?: LanguageType.English
        }
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
        locale: LanguageType,
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
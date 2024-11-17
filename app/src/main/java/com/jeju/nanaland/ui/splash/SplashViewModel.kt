package com.jeju.nanaland.ui.splash

import androidx.lifecycle.ViewModel
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
import com.jeju.nanaland.util.network.NetworkManager
import com.jeju.nanaland.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
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
    /*private val application: Application,*/
) : /*Android*/ViewModel(/*application*/) {
    private val SPLASH_TIME = 1500L

    private val _checkingState = MutableStateFlow(SplashCheckingState.Init)
    val checkingState = _checkingState.asStateFlow()

    fun checkProcess(deepLinkData: DeepLinkData) {
        _checkingState.update { SplashCheckingState.Init }

        viewModelScope.launch {
            var splashCheckingState = SplashCheckingState.Network
            val checkJob = launch {
                splashCheckingState = if (!networkManager.isNetworkConnected) {
                    SplashCheckingState.Network
                } else if (!checkLanguageState(deepLinkData.language)) {
                    SplashCheckingState.Language
                } else {
                    withTimeoutOrNull(SPLASH_TIME * 3) {
                        if (checkAuthState())
                            SplashCheckingState.Success
                        else
                            SplashCheckingState.Authorization
                    } ?: SplashCheckingState.Network
                }
            }
            val minAnimTimeJob = launch { delay(SPLASH_TIME) }

            checkJob.join()
            minAnimTimeJob.join()

            _checkingState.update { splashCheckingState }
        }
    }

    private suspend fun checkLanguageState(language: String?): Boolean {
        if(language != null) {
            setLanguageUseCase(LanguageType.codeToLanguage(language))
            return true
        } else {
            val lang = getLanguageUseCase().firstOrNull() ?: return false

            setLanguageUseCase(lang)
            return true
        }
    }
    private suspend fun checkAuthState(): Boolean {
        val refreshToken = getRefreshTokenUseCase().firstOrNull()
        if (refreshToken.isNullOrEmpty())
            return false

        val token = reissueAccessTokenUseCase(refreshToken).firstOrNull() ?: return false
        if(token !is NetworkResult.Success || token.data == null)
            return false

        saveAccessTokenUseCase(token.data.accessToken)
        saveRefreshTokenUseCase(token.data.refreshToken)

        val profile = getUserProfileUseCase().firstOrNull() ?: return false
        if(profile !is NetworkResult.Success || profile.data == null)
            return false

        UserData.provider = profile.data.provider
        if (UserData.provider == "GUEST")
            UserData.nickname = "GUEST"
        else
            UserData.nickname = profile.data.nickname

        return true
    }
}
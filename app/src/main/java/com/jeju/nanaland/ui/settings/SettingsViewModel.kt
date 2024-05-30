package com.jeju.nanaland.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.member.SignOutUseCase
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
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase
) : ViewModel() {

    fun signOut(
        moveToSignInScreen: () -> Unit
    ) {
        signOutUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    saveAccessTokenUseCase("")
                    saveRefreshTokenUseCase("")
                    moveToSignInScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "signOut") }
            .launchIn(viewModelScope)
    }
}
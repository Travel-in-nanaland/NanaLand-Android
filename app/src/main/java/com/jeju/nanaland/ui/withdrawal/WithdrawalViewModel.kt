package com.jeju.nanaland.ui.withdrawal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import com.jeju.nanaland.domain.usecase.authdatastore.ClearAuthDataStoreUseCase
import com.jeju.nanaland.domain.usecase.member.WithdrawUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.ClearRecentSearchDataStoreUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.ClearUserSettingsDataStoreUseCase
import com.jeju.nanaland.globalvalue.type.WithdrawalReasonType
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
class WithdrawalViewModel @Inject constructor(
    private val withdrawUseCase: WithdrawUseCase,
    private val clearAuthDataStoreUseCase: ClearAuthDataStoreUseCase,
    private val clearUserSettingsDataStoreUseCase: ClearUserSettingsDataStoreUseCase,
    private val clearRecentSearchDataStoreUseCase: ClearRecentSearchDataStoreUseCase,
) : ViewModel() {

    private val _selectedReason = MutableStateFlow(WithdrawalReasonType.Idle)
    val selectedReason = _selectedReason.asStateFlow()

    fun updateSelectedReason(reason: WithdrawalReasonType) {
        _selectedReason.update { reason }
    }

    fun withdraw(moveToSignInScreen: () -> Unit, isForce:Boolean) {
        if (_selectedReason.value == WithdrawalReasonType.Idle) {
            return
        }
        val requestData = WithdrawalRequest(
            withdrawalType = when (_selectedReason.value) {
                WithdrawalReasonType.InsufficientContent -> "INSUFFICIENT_CONTENT"
                WithdrawalReasonType.InconvenientService -> "INCONVENIENT_SERVICE"
                WithdrawalReasonType.InconvenientCommunity -> "INCONVENIENT_COMMUNITY"
                WithdrawalReasonType.RareVisits -> "RARE_VISITS"
                else -> ""
            }
        )
        withdrawUseCase(requestData, isForce)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    clearAuthDataStoreUseCase()
                    clearUserSettingsDataStoreUseCase()
                    clearRecentSearchDataStoreUseCase()
                    moveToSignInScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "withdrawUseCase") }
            .launchIn(viewModelScope)
    }
}
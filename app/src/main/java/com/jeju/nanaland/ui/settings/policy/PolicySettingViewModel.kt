package com.jeju.nanaland.ui.settings.policy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdatePolicyAgreementRequest
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.member.UpdatePolicyAgreementUseCase
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
class PolicySettingViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updatePolicyAgreementUseCase: UpdatePolicyAgreementUseCase,
) : ViewModel() {

    private val _isMarketingPolicyAgreed = MutableStateFlow(false)
    val isMarketingPolicyAgreed = _isMarketingPolicyAgreed.asStateFlow()
    private val _isLocationPolicyAgreed = MutableStateFlow(false)
    val isLocationPolicyAgreed = _isLocationPolicyAgreed.asStateFlow()

    private fun getUserProfile() {
        getUserProfileUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _isMarketingPolicyAgreed.update {
                            data.data.consentItem.first { it.consentType == "MARKETING" }.consent
                        }
                        _isLocationPolicyAgreed.update {
                            data.data.consentItem.first { it.consentType == "LOCATION_SERVICE" }.consent
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getUserProfileUseCase") }
            .launchIn(viewModelScope)
    }

    fun updatePolicyAgreement(consentType: String, consent: Boolean) {
        val requestData = UpdatePolicyAgreementRequest(
            consentType = consentType,
            consent = consent
        )
        updatePolicyAgreementUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    if (consentType == "MARKETING") {
                        _isMarketingPolicyAgreed.update { consent }
                    } else {
                        _isLocationPolicyAgreed.update { consent }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "updatePolicyAgreementUseCase") }
            .launchIn(viewModelScope)
    }

    init {
        getUserProfile()
    }
}
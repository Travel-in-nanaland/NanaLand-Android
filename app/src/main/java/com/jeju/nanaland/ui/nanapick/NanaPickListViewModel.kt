package com.jeju.nanaland.ui.nanapick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickListRequest
import com.jeju.nanaland.domain.usecase.nanapick.GetNanaPickListUseCase
import com.jeju.nanaland.domain.usecase.nanapick.GetRecommendedNanaPickListUseCase
import com.jeju.nanaland.globalvalue.constant.PAGING_SIZE
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NanaPickListViewModel @Inject constructor(
    private val getNanaPickListUseCase: GetNanaPickListUseCase,
    private val getRecommendedNanaPickListUseCase: GetRecommendedNanaPickListUseCase
) : ViewModel() {

    private val _nanaPickList = MutableStateFlow<UiState<List<NanaPickBannerData>>>(UiState.Loading)
    val nanaPickList = _nanaPickList.asStateFlow()
    private val _recommendedNanaPickList = MutableStateFlow<UiState<List<NanaPickBannerData>>>(UiState.Loading)
    val recommendedNanaPickList = _recommendedNanaPickList.asStateFlow()
    
    fun getNanaPickList() {
        _nanaPickList.update { UiState.Loading }
        val requestData = GetNanaPickListRequest(
            page = 0,
            size = PAGING_SIZE
        )
        getNanaPickListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _nanaPickList.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getNanaPickListUseCase") }
            .launchIn(viewModelScope)
    }

    fun getRecommendedNanaPickList() {
        getRecommendedNanaPickListUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _recommendedNanaPickList.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getRecommendedNanaPickListUseCase") }
            .launchIn(viewModelScope)
    }
}
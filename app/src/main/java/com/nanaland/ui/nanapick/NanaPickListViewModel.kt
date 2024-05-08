package com.nanaland.ui.nanapick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.nanaland.domain.request.nanapick.GetNanaPickListRequest
import com.nanaland.domain.usecase.nanapick.GetNanaPickListUseCase
import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.onError
import com.nanaland.util.network.onException
import com.nanaland.util.network.onSuccess
import com.nanaland.util.ui.UiState
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
    private val getNanaPickListUseCase: GetNanaPickListUseCase
) : ViewModel() {

    private val _nanaPickList = MutableStateFlow<UiState<List<NanaPickBannerData>>>(UiState.Loading)
    val nanaPickList = _nanaPickList.asStateFlow()
    
    fun getNanaPickList() {
        _nanaPickList.update { UiState.Loading }
        val requestData = GetNanaPickListRequest(
            page = 0,
            size = 12
        )
        getNanaPickListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _nanaPickList.update {
                            UiState.Success(data.data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "flow Error") }
            .launchIn(viewModelScope)
    }
}
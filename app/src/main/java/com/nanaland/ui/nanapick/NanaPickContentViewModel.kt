package com.nanaland.ui.nanapick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.BuildConfig
import com.nanaland.domain.entity.nanapick.NanaPickContent
import com.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import com.nanaland.domain.usecase.nanapick.GetNanaPickContentUseCase
import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.finally
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
class NanaPickContentViewModel @Inject constructor(
    private val getNanaPickContentUseCase: GetNanaPickContentUseCase
) : ViewModel() {

    private val _nanaPickContent = MutableStateFlow<UiState<NanaPickContent>>(UiState.Empty)
    val nanaPickContent = _nanaPickContent.asStateFlow()

    fun getNanaPickContent(contentId: Long?) {
        if (contentId == null) return;
        _nanaPickContent.update { UiState.Loading }
        val requestData = GetNanaPickContentRequest(contentId)
        getNanaPickContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _nanaPickContent.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { _, _ ->

                }.onException { _ ->

                }.finally { _ ->
                    LogUtil.printNetworkLog(requestData, networkResult, "GetNanaPickContentUseCase")
                }
            }
            .catch { LogUtil.printLog("flow Error", "flow Error") }
            .launchIn(viewModelScope)
    }
}
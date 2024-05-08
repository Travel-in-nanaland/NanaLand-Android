package com.nanaland.ui.nanapick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.nanapick.NanaPickContentData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.nanaland.domain.usecase.nanapick.GetNanaPickContentUseCase
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
class NanaPickContentViewModel @Inject constructor(
    private val getNanaPickContentUseCase: GetNanaPickContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _nanaPickContent = MutableStateFlow<UiState<NanaPickContentData>>(UiState.Loading)
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
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "flow Error") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Long?) {
        if (contentId == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "NANA"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _nanaPickContent.update { uiState ->
                            if (uiState is UiState.Success) {
                                UiState.Success(uiState.data.copy(favorite = data.data.favorite))
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }
}
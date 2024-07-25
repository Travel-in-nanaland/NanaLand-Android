package com.jeju.nanaland.ui.nanapick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.nanapick.GetNanaPickContentUseCase
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
class NanaPickContentViewModel @Inject constructor(
    private val getNanaPickContentUseCase: GetNanaPickContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _nanaPickContent = MutableStateFlow<UiState<NanaPickContentData>>(UiState.Loading)
    val nanaPickContent = _nanaPickContent.asStateFlow()

    fun getNanaPickContent(contentId: Int?) {
        if (contentId == null) return;
        _nanaPickContent.update { UiState.Loading }
        val requestData = GetNanaPickContentRequest(contentId)
        getNanaPickContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _nanaPickContent.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getNanaPickContentUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Int?) {
        if (contentId == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "NANA"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _nanaPickContent.update { uiState ->
                            if (uiState is UiState.Success) {
                                UiState.Success(uiState.data.copy(favorite = data.favorite))
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "toggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }
}
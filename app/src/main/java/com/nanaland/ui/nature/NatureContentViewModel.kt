package com.nanaland.ui.nature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.nature.NatureContentData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.nature.GetNatureContentRequest
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.nanaland.domain.usecase.nature.GetNatureContentUseCase
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
class NatureContentViewModel @Inject constructor(
    private val getNatureContentUseCase: GetNatureContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _natureContent = MutableStateFlow<UiState<NatureContentData>>(UiState.Loading)
    val natureContent = _natureContent.asStateFlow()

    fun getNatureContent(contentId: Long?, isSearch: Boolean) {
        if (contentId == null) return
        _natureContent.update { UiState.Loading }
        val requestData = GetNatureContentRequest(
            id = contentId,
            isSearch = isSearch
        )
        getNatureContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _natureContent.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow error", "GetNatureContentUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Long, updateList: (Long, Boolean) -> Unit) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "NATURE"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _natureContent.update { uiState ->
                            if (uiState is UiState.Success) {
                                updateList(contentId, data.data.favorite)
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
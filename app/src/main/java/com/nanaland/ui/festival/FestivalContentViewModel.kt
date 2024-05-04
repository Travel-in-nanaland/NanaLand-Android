package com.nanaland.ui.festival

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.festival.FestivalContentData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.festival.GetFestivalContentRequest
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.nanaland.domain.usecase.festival.GetFestivalContentUseCase
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
class FestivalContentViewModel @Inject constructor(
    private val getFestivalContentUseCase: GetFestivalContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _festivalContent = MutableStateFlow<UiState<FestivalContentData>>(UiState.Loading)
    val festivalContent = _festivalContent.asStateFlow()

    fun getFestivalContent(contentId: Long?, isSearch: Boolean) {
        if (contentId == null) return
        _festivalContent.update { UiState.Loading }
        val requestData = GetFestivalContentRequest(
            id = contentId,
            isSearch = isSearch
        )
        getFestivalContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _festivalContent.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow error", "GetFestivalContentUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Long, updateList: (Long, Boolean) -> Unit) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "FESTIVAL"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _festivalContent.update { uiState ->
                            if (uiState is UiState.Success) {
                                updateList(contentId, data.data.favorite)
                                UiState.Success(uiState.data.copy(favorite = data.data.favorite))
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }
}
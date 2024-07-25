package com.jeju.nanaland.ui.festival

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.festival.FestivalContentData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.festival.GetFestivalContentRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.festival.GetFestivalContentUseCase
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
class FestivalContentViewModel @Inject constructor(
    private val getFestivalContentUseCase: GetFestivalContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _festivalContent = MutableStateFlow<UiState<FestivalContentData>>(UiState.Loading)
    val festivalContent = _festivalContent.asStateFlow()

    fun getFestivalContent(contentId: Int?, isSearch: Boolean) {
        if (contentId == null) return
        _festivalContent.update { UiState.Loading }
        val requestData = GetFestivalContentRequest(
            id = contentId,
            isSearch = isSearch
        )
        getFestivalContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _festivalContent.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "getFestivalContentUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Int, updateList: (Int, Boolean) -> Unit) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "FESTIVAL"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _festivalContent.update { uiState ->
                            if (uiState is UiState.Success) {
                                updateList(contentId, data.favorite)
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
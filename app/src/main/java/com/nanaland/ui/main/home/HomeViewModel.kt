package com.nanaland.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.member.RecommendedPostData
import com.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.nanaland.domain.usecase.member.GetRecommendedPostUseCase
import com.nanaland.domain.usecase.nanapick.GetHomePreviewBannerUseCase
import com.nanaland.globalvalue.type.HomeScreenViewType
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
class HomeViewModel @Inject constructor(
    private val getHomePreviewBannerUseCase: GetHomePreviewBannerUseCase,
    private val getRecommendedPostUseCase: GetRecommendedPostUseCase
) : ViewModel() {

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()
    private val _viewType = MutableStateFlow(HomeScreenViewType.Home)
    val viewType = _viewType.asStateFlow()
    private val _homeBannerPreview = MutableStateFlow<UiState<List<NanaPickBannerData>>>(UiState.Loading)
    val homeBannerPreview = _homeBannerPreview.asStateFlow()
    private val _recommendedPost = MutableStateFlow<UiState<List<RecommendedPostData>>>(UiState.Loading)
    val recommendedPosts = _recommendedPost.asStateFlow()

    fun updateInputText(text: String) {
        _inputText.update { text }
    }

    fun updateHomeScreenViewType(viewType: HomeScreenViewType) {
        _viewType.update { viewType }
    }

    fun getHomeBannerPreview() {
        _homeBannerPreview.update { UiState.Loading }
        getHomePreviewBannerUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _homeBannerPreview.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "GetHomePreviewBannerUseCase") }
            .launchIn(viewModelScope)
    }

    fun getRecommendedPost() {
        _recommendedPost.update { UiState.Loading }
        getRecommendedPostUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _recommendedPost.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "GetRecommendedPostUseCase") }
            .launchIn(viewModelScope)
    }
}
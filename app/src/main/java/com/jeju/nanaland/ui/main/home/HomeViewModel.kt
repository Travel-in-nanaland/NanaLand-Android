package com.jeju.nanaland.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.domain.usecase.member.GetRecommendedPostUseCase
import com.jeju.nanaland.domain.usecase.nanapick.GetHomePreviewBannerUseCase
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
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

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getHomePreviewBannerUseCase") }
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

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getRecommendedPostUseCase") }
            .launchIn(viewModelScope)
    }
}
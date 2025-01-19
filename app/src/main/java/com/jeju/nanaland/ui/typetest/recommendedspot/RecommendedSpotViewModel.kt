package com.jeju.nanaland.ui.typetest.recommendedspot

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.domain.usecase.member.GetRecommendedPostUseCase
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecommendedSpotViewModel @Inject constructor(
    private val getRecommendedPostUseCase: GetRecommendedPostUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val stateHandle: ROUTE.RecommendedSpot = savedStateHandle.toRoute()

    private val _recommendedPostList = MutableStateFlow(emptyList<RecommendedPostData>())
    val recommendedPostList = _recommendedPostList.asStateFlow()

    private fun getRecommendedSpot(memberId: Int? = null) {
        getRecommendedPostUseCase(memberId)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _recommendedPostList.update { data }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "getRecommendedSpot") }
            .launchIn(viewModelScope)
    }

    init {
        getRecommendedSpot(stateHandle.memberId)
    }
}
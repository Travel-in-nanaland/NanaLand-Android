package com.jeju.nanaland.ui.noticeDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.entity.notice.NoticeDetail
import com.jeju.nanaland.domain.usecase.board.GetNoticeUseCase
import com.jeju.nanaland.globalvalue.constant.ROUTE
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
class BoardViewModel @Inject constructor(
    private val getNoticeUseCase: GetNoticeUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val stateHandle: ROUTE.NoticeDetail = savedStateHandle.toRoute()

    private val _noticeData = MutableStateFlow<UiState<NoticeDetail>>(UiState.Loading)
    val noticeData = _noticeData.asStateFlow()

    init {
        getNoticeUseCase(stateHandle.noticeId)
            .onEach { networkResult ->
                networkResult.onSuccess { _, _, data ->
                    data?.let {
                        _noticeData.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->
                }.onException {
                }
            }
            .catch { LogUtil.e("flow Error", "GetNoticeUseCase") }
            .launchIn(viewModelScope)
    }

}
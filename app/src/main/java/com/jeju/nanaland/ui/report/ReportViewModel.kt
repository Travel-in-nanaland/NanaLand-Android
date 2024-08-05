package com.jeju.nanaland.ui.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.usecase.report.ReportReviewUseCase
import com.jeju.nanaland.globalvalue.type.ReportType
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportReviewUseCase: ReportReviewUseCase
) : ViewModel() {

    private val _page = MutableStateFlow(1)
    val page = _page.asStateFlow()

    private val _submitCallState = MutableStateFlow<UiState<Unit>?>(null)
    val submitCallState = _submitCallState.asStateFlow()

    var reportReason: ReportType? = null
        private set

    fun setPage(page: Int) {
        _page.update { page }
    }

    fun setReason(reason: ReportType){
        reportReason = reason
    }

    fun submit(
        reviewId: Int,
        claimType: ReportType,
        content: String,
        images: List<UriRequestBody>
    ) {
        reportReviewUseCase(
            reviewId,
            claimType,
            content,
            images,
        ).onEach {
            _submitCallState.update { UiState.Loading }
            it.onSuccess { code, message, data ->
                _submitCallState.update { UiState.Success(Unit) }
            }.onError { code, message ->
                _submitCallState.update { UiState.Failure("") }
            }
        }.launchIn(viewModelScope)
    }
}
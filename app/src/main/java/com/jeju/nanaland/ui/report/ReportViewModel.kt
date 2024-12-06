package com.jeju.nanaland.ui.report

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.entity.file.FileCategory
import com.jeju.nanaland.domain.entity.report.ClaimType
import com.jeju.nanaland.domain.entity.report.ReportDetail
import com.jeju.nanaland.domain.entity.report.ReportType
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.domain.usecase.file.PutFileUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.report.ReportReviewUseCase
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportReviewUseCase: ReportReviewUseCase,
    getUserProfileUseCase: GetUserProfileUseCase,
    private val putFileUseCase: PutFileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val stateHandle: ROUTE.Report = savedStateHandle.toRoute()

    private val _page = MutableStateFlow(1)
    val page = _page.asStateFlow()

    private val _submitCallState = MutableStateFlow<UiState<Unit>?>(null)
    val submitCallState = _submitCallState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    var reportReason: ClaimType? = null
        private set

    init {
        getUserProfileUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _email.update { data.email }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun setPage(page: Int) {
        _page.update { page }
    }

    fun setReason(reason: ClaimType){
        reportReason = reason
    }

    fun submit(
        email: String,
        claimType: ClaimType,
        content: String,
        images: List<String>
    ) {
        viewModelScope.launch {
            _submitCallState.update { UiState.Loading }

            reportReviewUseCase(
                ReportDetail(
                    id = stateHandle.reportId,
                    reportType = if(stateHandle.isReview) ReportType.REVIEW else ReportType.MEMBER,
                    email = email,
                    claimType = claimType,
                    content = content,
                    images = images.map {
                        putFileUseCase(it, FileCategory.ClaimReport)
                    }
                ),
            ).onEach {
                it.onSuccess { code, message, data ->
                    _submitCallState.update { UiState.Success(Unit) }
                }.onError { code, message ->
                    _submitCallState.update { UiState.Failure("") }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun setSubmitCallStateNull(){
        _submitCallState.update { null }
    }
}
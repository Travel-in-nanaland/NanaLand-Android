package com.jeju.nanaland.ui.reviewwrite

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.domain.usecase.review.CreateReviewUseCase
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.globalvalue.type.ReviewKeyword
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ReviewWriteUiState(
    val titleImg: String = "",
    val titleTxt: String = "",
    val subTitleTxt: String = "",

    val reviewRating: Int = 0,
    val reviewImage: List<Uri> = emptyList(),
    val reviewKeyword: List<ReviewKeyword> = emptyList(),

    val canSubmit: Boolean = false
)

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val createReviewUseCase: CreateReviewUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    companion object{
        const val MAX_IMAGE_CNT = 5
        const val MAX_TEXT_LENGTH = 200
        const val MIN_KEYWORD_CNT = 3
        const val MAX_KEYWORD_CNT = 6
    }

    private val viewModelState = MutableStateFlow(
        ReviewWriteUiState()
    )
    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    var reviewText by mutableStateOf("")
        private set

    private val _callState = MutableStateFlow<UiState<Unit>?>(null)
    val callState = _callState.asStateFlow()


    fun setUI(image: String, title: String, address: String) {
        viewModelState.update {
            it.copy(
               titleImg = image,
               titleTxt = title,
                subTitleTxt = address,
            )
        }
    }


    fun submit(
        id: Int,
        category: ReviewCategoryType,
        snapshotData: ReviewWriteUiState,
        snapshotContent: String
    ) {
        viewModelScope.launch {
            _callState.update { UiState.Loading }
            createReviewUseCase(
                id = id,
                category = category,
                images = snapshotData.reviewImage.map {
                    UriRequestBody(context, it)
                },
                data = CreateReviewRequest(
                    rating = snapshotData.reviewRating,
                    content = snapshotContent,
                    keywords = snapshotData.reviewKeyword,
                ),
            ).onSuccess { _, _, _ ->
                _callState.update { UiState.Success(Unit) }
            }.onError { _, _ ->
                _callState.update { null }
            }.onException {
                _callState.update { null }
            }
        }
    }


    fun updateRating(arg: Int) {
        viewModelState.update {
            it.copy(reviewRating = arg).setCanSubmit()

        }
    }

    fun changeImage(arg: List<Uri>) {
        viewModelState.update {
            it.copy(
                reviewImage = arg.take(MAX_IMAGE_CNT)
            )
        }
    }

    fun setKeyword(arg: List<ReviewKeyword>) {
        viewModelState.update {
            it.copy(
                reviewKeyword = arg.take(MAX_KEYWORD_CNT)
            ).setCanSubmit()
        }
    }
    fun removeKeyword(arg: ReviewKeyword) {
        viewModelState.update {
            it.copy(
                reviewKeyword = it.reviewKeyword.minus(arg)
            ).setCanSubmit()
        }
    }

    fun updateReviewText(arg: String) {
        reviewText = arg
        viewModelState.update {
            it.setCanSubmit()
        }
    }

    private fun ReviewWriteUiState.setCanSubmit(): ReviewWriteUiState {
        val canSubmit = reviewRating > 0 &&
                reviewText.isNotEmpty() && reviewText.length <= MAX_TEXT_LENGTH &&
                reviewKeyword.size in (MIN_KEYWORD_CNT..MAX_KEYWORD_CNT)

        return if (this.canSubmit != canSubmit)
            copy(canSubmit = canSubmit)
        else
            this
    }
}
package com.jeju.nanaland.ui.reviewwrite

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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
    val reviewKeyword: List<String> = emptyList(),

    val canSubmit: Boolean = false
)

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
) : ViewModel() {
    companion object{
        const val MAX_IMAGE_CNT = 5
        const val MAX_TEXT_LENGTH = 200
        const val MIN_KEYWORD_CNT = 3
        const val MAX_KEYWORD_CNT = 6

        val TEMP_KEYWORD_LIST = listOf(
            Pair("분위기", listOf(
                "기념일에 가면 좋아요",
                "아기자기해요",
                "고급스러워요",
                "풍경이 예뻐요",
                "친절해요"
            )),
            Pair("동반자", listOf(
                "자녀",
                "친구",
                "부모님",
                "혼자",
                "연인/배우자",
                "친척/형제",
                "반려동물"
            )),
             Pair("편의 시설", listOf(
                "콘센트 사용 가능",
                "넓은 장소",
                "주차장",
                "깨끗한 화장실"
             ))
        )
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

    init {
        viewModelScope.launch {
            fakeGetDataFromAPI()
        }
    }

    private suspend fun fakeGetDataFromAPI() {
        Thread.sleep(800)

        viewModelState.update {
            it.copy(
                titleImg = "https://picsum.photos/200/300" ,
                titleTxt = "월정 투명카약" ,
                subTitleTxt = "제주특별자치도 제주시 구좌읍 월정리 1400-33" ,
            )
        }
    }


    fun updateRating(arg: Int) {
        viewModelState.update {
            it.copy(reviewRating = arg).setCanSubmit()

        }
    }

    fun addImage(arg: List<Uri>) {
        viewModelState.update {
            it.copy(
                reviewImage = it.reviewImage.plus(arg).take(MAX_IMAGE_CNT)
            )
        }
    }
    fun removeImage(arg: Uri) {
        viewModelState.update {
            it.copy(
                reviewImage = it.reviewImage.minus(arg)
            )
        }
    }

    fun setKeyword(arg: List<String>) {
        viewModelState.update {
            it.copy(
                reviewKeyword = arg.take(MAX_KEYWORD_CNT)
            ).setCanSubmit()
        }
    }
    fun removeKeyword(arg: String) {
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
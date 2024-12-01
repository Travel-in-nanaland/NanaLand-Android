package com.jeju.nanaland.ui.reviewwrite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.file.FileCategory
import com.jeju.nanaland.domain.entity.review.ReviewKeywordResult
import com.jeju.nanaland.domain.request.experience.GetExperienceContentRequest
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantContentRequest
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.domain.request.review.EditImage
import com.jeju.nanaland.domain.usecase.experience.GetExperienceContentUseCase
import com.jeju.nanaland.domain.usecase.file.PutFileUseCase
import com.jeju.nanaland.domain.usecase.restaurant.GetRestaurantContentUseCase
import com.jeju.nanaland.domain.usecase.review.CreateReviewUseCase
import com.jeju.nanaland.domain.usecase.review.GetMyReviewDetailUseCase
import com.jeju.nanaland.domain.usecase.review.GetReviewListByKeywordUseCase
import com.jeju.nanaland.domain.usecase.review.ModifyUserReviewUseCase
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.globalvalue.type.ReviewKeyword
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.io.IOException
import javax.inject.Inject


data class ReviewWriteUiState(
    val titleImg: String = "",
    val titleTxt: String = "",
    val subTitleTxt: String = "",

    val reviewRating: Int = 0,
    val reviewImage: List<Pair<Int,String>> = emptyList(),
    val reviewKeyword: List<ReviewKeyword> = emptyList(),

    val canSubmit: Boolean = false
)

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val createReviewUseCase: CreateReviewUseCase,
    private val modifyUserReviewUseCase: ModifyUserReviewUseCase,
    private val getExperienceListUseCase: GetExperienceContentUseCase,
    private val getRestaurantContentUseCase: GetRestaurantContentUseCase,
    private val getMyReviewDetailUseCase: GetMyReviewDetailUseCase,
    private val getReviewListByKeywordUseCase: GetReviewListByKeywordUseCase,
    private val putFileUseCase: PutFileUseCase,
) : ViewModel() {
    companion object{
        const val MAX_IMAGE_CNT = 5
        const val MAX_TEXT_LENGTH = 200
        const val MIN_KEYWORD_CNT = 1
        const val MAX_KEYWORD_CNT = 6
    }
    var isEdit = false
        private set

    private val viewModelState = MutableStateFlow(
        ReviewWriteUiState()
    )
    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    val keywordText = MutableStateFlow("")
    var reviewText by mutableStateOf("")
        private set

    private val _reviewsByKeyword: MutableStateFlow<UiState<List<ReviewKeywordResult>>> =
        MutableStateFlow(UiState.Success(emptyList()))
    val reviewsByKeyword = _reviewsByKeyword.asStateFlow()

    init {
        @Suppress("OPT_IN_USAGE")
        keywordText.filter { it.isNotBlank() }
            .onEach {
                if(_reviewsByKeyword.value != UiState.Loading)
                    _reviewsByKeyword.update { UiState.Loading }
            }
            .debounce(600)
            .apply {
                if(_reviewsByKeyword.value != UiState.Loading)
                    distinctUntilChanged()
            }
            .flatMapLatest {
                flow<Unit> {
                    val response = withTimeoutOrNull(2000) {
                        getReviewListByKeywordUseCase(it)
                    } ?: NetworkResult.Exception(IOException("network timeout 2000ms"))

                    response.onSuccess { code, message, data ->
                        data?.let {
                            _reviewsByKeyword.update { UiState.Success(data) }
                        }
                    }.onError { code, message ->
                        _reviewsByKeyword.update { UiState.Failure("") }
                    }.onException { e ->
                        _reviewsByKeyword.update { UiState.Failure("", e) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private val _callState = MutableStateFlow<UiState<Unit>?>(null)
    val callState = _callState.asStateFlow()


    fun init(id: Int?, category: ReviewCategoryType?, isEdit: Boolean = false){
        if(id == null) return

        this.isEdit = isEdit
        if(isEdit) { // edit my review
            getMyReviewDetailUseCase(id).onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        viewModelState.update {
                            ReviewWriteUiState(
                                titleImg = data.firstImage.originUrl,
                                titleTxt = data.title,
                                subTitleTxt = data.address,
                                reviewRating = data.rating.toInt(),
                                reviewImage = data.images.map { Pair(it.id, it.originUrl) },
                                reviewKeyword = data.reviewKeywords.map { keyword ->
                                    ReviewKeyword.Mood.entries.firstOrNull {
                                        it.name == keyword
                                    } ?: ReviewKeyword.With.entries.firstOrNull {
                                        it.name == keyword
                                    } ?: ReviewKeyword.Infra.valueOf(keyword)
                                },
                                canSubmit = true
                            )
                        }
                        reviewText = data.content
                    }
                }
            }.launchIn(viewModelScope)
        } else { // create new review
            when(category){
                ReviewCategoryType.EXPERIENCE -> getExperienceListUseCase(
                    GetExperienceContentRequest(id, false)
                ).onEach { networkResult ->
                    networkResult.onSuccess { code, message, data ->
                        data?.let {
                            viewModelState.update {
                                ReviewWriteUiState(
                                    titleImg = data.images.firstOrNull()?.originUrl ?: "",
                                    titleTxt = data.title,
                                    subTitleTxt = data.address
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)

                ReviewCategoryType.RESTAURANT -> getRestaurantContentUseCase(
                    GetRestaurantContentRequest(id,false)
                ).onEach { networkResult ->
                    networkResult.onSuccess { code, message, data ->
                        data?.let {
                            viewModelState.update {
                                it.copy(
                                    titleImg = data.images.firstOrNull()?.originUrl ?: "",
                                    titleTxt = data.title,
                                    subTitleTxt = data.address
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)

                else -> return
            }
        }
    }

    fun submit(
        id: Int,
        category: ReviewCategoryType,
        snapshotData: ReviewWriteUiState,
        snapshotContent: String,
        newImages: List<String>
    ) {
        viewModelScope.launch {
            _callState.update { UiState.Loading }
            run {
                val imageFileKeys = newImages.map {
                    putFileUseCase(it, FileCategory.Review)
                }
                if(!isEdit)
                    createReviewUseCase(
                        id = id,
                        category = category,
                        data = CreateReviewRequest(
                            rating = snapshotData.reviewRating,
                            content = snapshotContent,
                            images = imageFileKeys,
                            keywords = snapshotData.reviewKeyword,
                        )
                    )
                else
                    modifyUserReviewUseCase(
                        id = id,
                        data = CreateReviewRequest(
                            rating = snapshotData.reviewRating,
                            content = snapshotContent,
                            keywords = snapshotData.reviewKeyword,
                            images = imageFileKeys,
                            editImages = snapshotData.reviewImage.map {
                                EditImage(it.first, it.first == -1)
                            }
                        )
                    )
            }.onSuccess { _, _, _ ->
                _callState.update { UiState.Success(Unit) }
            }.onError { _, _ ->
                _callState.update { UiState.Failure("") }
            }.onException {
                _callState.update { UiState.Failure("") }
            }
        }
    }
    fun setCallStateNull(){
        _callState.update { null }
    }


    fun updateRating(arg: Int) {
        viewModelState.update {
            it.copy(reviewRating = arg).setCanSubmit()

        }
    }

    fun changeImage(arg: List<Pair<Int,String>>) {
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
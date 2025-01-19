package com.jeju.nanaland.ui.experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.request.experience.GetExperienceContentRequest
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import com.jeju.nanaland.domain.usecase.experience.GetExperienceContentUseCase
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.review.DeleteReviewUseCase
import com.jeju.nanaland.domain.usecase.review.GetReviewListByPostUseCase
import com.jeju.nanaland.domain.usecase.review.ToggleReviewFavoriteUseCase
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.NetworkResult
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
class ExperienceContentViewModel @Inject constructor(
    private val getExperienceContentUseCase: GetExperienceContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getReviewListUseCase: GetReviewListByPostUseCase,
    private val toggleReviewFavoriteUseCase: ToggleReviewFavoriteUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
) : ViewModel() {

    private val _experienceContent = MutableStateFlow<UiState<ExperienceContent>>(UiState.Loading)
    val experienceContent = _experienceContent.asStateFlow()
    private val _reviewList = MutableStateFlow<UiState<ReviewListData>>(UiState.Loading)
    val reviewList = _reviewList.asStateFlow()

    fun getExperienceContent(contentId: Int?, isSearch: Boolean) {
        if (contentId == null) return

        _experienceContent.update { UiState.Loading }
        val requestData = GetExperienceContentRequest(
            id = contentId,
            isSearch = isSearch
        )
        getExperienceContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _experienceContent.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "getExperienceContentUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Int, updateList: (Int, Boolean) -> Unit) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "EXPERIENCE"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _experienceContent.update { uiState ->
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

    suspend fun setRemove(id: Int): NetworkResult<String> {
        return deleteReviewUseCase(DeleteReviewRequest(id))
    }
    fun getReview(contentId: Int?) {
        if (contentId == null) return
        val requestData = GetReviewListByPostRequest(
            id = contentId,
            category = ReviewCategoryType.EXPERIENCE,
            page = 0,
            size = 3
        )
        getReviewListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _reviewList.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {  }
            }
            .catch { LogUtil.e("flow Error", "getReviewListUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleReviewFavorite(reviewId: Int) {
        val requestData = ToggleReviewFavoriteRequest(
            id = reviewId
        )
        toggleReviewFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _reviewList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.data.map { item ->
                                    if (item.id == reviewId) item.copy(
                                        reviewHeart = data.reviewHeart,
                                        heartCount = if (data.reviewHeart) item.heartCount + 1 else item.heartCount - 1
                                    )
                                    else item
                                }
                                UiState.Success(uiState.data.copy(data = newList))
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
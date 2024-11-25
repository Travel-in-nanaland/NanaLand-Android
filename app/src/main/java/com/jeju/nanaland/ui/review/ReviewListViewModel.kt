package com.jeju.nanaland.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.review.DeleteReviewUseCase
import com.jeju.nanaland.domain.usecase.review.GetReviewListByPostUseCase
import com.jeju.nanaland.domain.usecase.review.ToggleReviewFavoriteUseCase
import com.jeju.nanaland.globalvalue.constant.PAGING_SIZE
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
class ReviewListViewModel @Inject constructor(
    private val getReviewListUseCase: GetReviewListByPostUseCase,
    private val toggleReviewFavoriteUseCase: ToggleReviewFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
) : ViewModel() {

    private var page = 0
    private val _reviewCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val reviewCount = _reviewCount.asStateFlow()
    private val _reviewRating = MutableStateFlow<UiState<Double>>(UiState.Loading)
    val reviewRating = _reviewRating.asStateFlow()
    private val _reviewList = MutableStateFlow<UiState<List<ReviewData>>>(UiState.Loading)
    val reviewList = _reviewList.asStateFlow()
    private val _contentFavorite = MutableStateFlow(false)
    val contentFavorite = _contentFavorite.asStateFlow()

    fun getReviewList(contentId: Int?, category: String?) {
        if (contentId == null || category.isNullOrEmpty()) return
        var prevList: List<ReviewData>? = null
        if (_reviewList.value is UiState.Success) {
            page++
            prevList = (_reviewList.value as UiState.Success).data
        }
        val requestData = GetReviewListByPostRequest(
            id = contentId,
            category = when (category) {
                "NANA" -> ReviewCategoryType.NANA
                "RESTAURANT" -> ReviewCategoryType.RESTAURANT
                "EXPERIENCE" -> ReviewCategoryType.EXPERIENCE
                "NATURE" -> ReviewCategoryType.NATURE
                "FESTIVAL" -> ReviewCategoryType.FESTIVAL
                "MARKET" -> ReviewCategoryType.MARKET
                else -> ReviewCategoryType.NANA_CONTENT
            },
            page = page,
            size = PAGING_SIZE,
        )
        getReviewListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _reviewCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _reviewRating.update {
                            UiState.Success(data.totalAvgRating)
                        }
                        _reviewList.update {
                            if (prevList.isNullOrEmpty()) {
                                UiState.Success(data.data)
                            } else {
                                UiState.Success(prevList + data.data)
                            }
                        }
                    }
                }.onError { code, message ->

                }.onException {  }
            }
            .catch { LogUtil.e("flow Error", "getReviewListUseCase") }
            .launchIn(viewModelScope)
    }

    suspend fun setRemove(id: Int): NetworkResult<String> {
        return deleteReviewUseCase(DeleteReviewRequest(id))
    }
    fun toggleReviewFavorite(id: Int) {
        val requestData = ToggleReviewFavoriteRequest(
            id = id
        )
        toggleReviewFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _reviewList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.map { item ->
                                    if (item.id == id) item.copy(
                                        reviewHeart = data.reviewHeart,
                                        heartCount = if (data.reviewHeart) item.heartCount + 1 else item.heartCount - 1
                                    )
                                    else item
                                }
                                UiState.Success(newList)
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

    fun initContentFavorite(isFavorite: Boolean?) {
        if (isFavorite == null) return
        _contentFavorite.update { isFavorite }
    }

    fun toggleContentFavorite(id: Int?, category: String?) {
        if (id == null || category == null) return

        val requestData = ToggleFavoriteRequest(
            id = id,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _contentFavorite.update { uiState ->
                            data.favorite
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
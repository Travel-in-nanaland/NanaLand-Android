package com.jeju.nanaland.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantContentRequest
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.restaurant.GetRestaurantContentUseCase
import com.jeju.nanaland.domain.usecase.review.GetReviewListByPostUseCase
import com.jeju.nanaland.domain.usecase.review.ToggleReviewFavoriteUseCase
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
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
class RestaurantContentViewModel @Inject constructor(
    private val getRestaurantContentUseCase: GetRestaurantContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getReviewListUseCase: GetReviewListByPostUseCase,
    private val toggleReviewFavoriteUseCase: ToggleReviewFavoriteUseCase
) : ViewModel() {

    private val _restaurantContent = MutableStateFlow<UiState<RestaurantContentData>>(UiState.Loading)
    val restaurantContent = _restaurantContent.asStateFlow()
    private val _reviewList = MutableStateFlow<UiState<ReviewListData>>(UiState.Loading)
    val reviewList = _reviewList.asStateFlow()

    fun getRestaurantContent(contentId: Int?, isSearch: Boolean) {
        if (contentId == null) return

        _restaurantContent.update { UiState.Loading }
        val requestData = GetRestaurantContentRequest(
            id = contentId,
            isSearch = isSearch
        )
        getRestaurantContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _restaurantContent.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "getRestaurantContentUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Int, updateList: (Int, Boolean) -> Unit) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "RESTAURANT"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _restaurantContent.update { uiState ->
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

    fun getReview(contentId: Int?) {
        if (contentId == null) return
        val requestData = GetReviewListByPostRequest(
            id = contentId,
            category = ReviewCategoryType.RESTAURANT,
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
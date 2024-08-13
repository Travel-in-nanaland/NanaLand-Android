package com.jeju.nanaland.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.domain.entity.notice.NoticeSummery
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import com.jeju.nanaland.domain.usecase.board.GetNoticeListUseCase
import com.jeju.nanaland.domain.usecase.board.GetNoticeUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.review.DeleteReviewUseCase
import com.jeju.nanaland.domain.usecase.review.GetReviewListByUserUseCase
import com.jeju.nanaland.domain.usecase.review.ToggleReviewFavoriteUseCase
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getReviewListByUserUseCase: GetReviewListByUserUseCase,
    private val getNoticeUseCase: GetNoticeUseCase,
    private val getNoticeListUseCase: GetNoticeListUseCase,
    private val toggleReviewFavoriteUseCase: ToggleReviewFavoriteUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val userId: Int?
        get() = savedStateHandle["userId"]

    private val _isReviewList = MutableStateFlow(true)
    val isReviewList = _isReviewList.asStateFlow()

    private val _userProfile = MutableStateFlow<UiState<UserProfile>>(UiState.Loading)
    val userProfile = _userProfile.asStateFlow()

    val reviews = getReviewListByUserUseCase(userId)
        .flow
        .cachedIn(viewModelScope)

    val notices = run {
        if(userId != null) flow { PagingData.empty<NoticeSummery>() }
        else getNoticeListUseCase()
                .flow
                .cachedIn(viewModelScope)
    }
    val isGuest: Boolean
        get() = UserData.provider == "GUEST"

    fun setIsReviewList(isReviewList: Boolean){
        _isReviewList.update { isReviewList }
    }

    fun setLike(id: Int){
        val data = ToggleReviewFavoriteRequest(id = id)
        toggleReviewFavoriteUseCase(data)
            .onEach {
                it.onError { code, message ->  }
            }
    }

    fun setRemove(id: Int){
        deleteReviewUseCase(DeleteReviewRequest(id))
            .onEach {
                it
                    .onSuccess { code, message, data ->  }
                    .onError { code, message ->  }
            }
    }

    fun getUserProfile() {
        getUserProfileUseCase(userId)
            .onEach { networkResult ->
                networkResult.onSuccess { _, _, data ->
                    data?.let {
                        if(data == (_userProfile.value as? UiState.Success)?.data)
                            return@onSuccess

                        _userProfile.update {
                            UiState.Success(data)
                        }

                        if(userId != null)
                            return@onSuccess

                        UserData.provider = data.provider ?: "GUEST"
                        if (isGuest) {
                            UserData.nickname = "GUEST"
                        } else {
                            UserData.nickname = data.nickname ?: "GUEST"
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getUserProfileUseCase") }
            .launchIn(viewModelScope)
    }

}
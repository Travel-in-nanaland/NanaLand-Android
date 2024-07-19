package com.jeju.nanaland.ui.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.mypage.TempNoticeData
import com.jeju.nanaland.ui.component.mypage.TempReviewData
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UiState<UserProfile>>(UiState.Loading)
    val userProfile = _userProfile.asStateFlow()

    private val _reviews = MutableStateFlow<List<TempReviewData>>(emptyList())
    val reviews = _reviews.asStateFlow()
    private val _notices = MutableStateFlow<List<TempNoticeData>>(emptyList())
    val notices = _notices.asStateFlow()

    init {
        fakeApiCall()
    }

    fun getUserProfile() {
        getUserProfileUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _userProfile.update {
                            UiState.Success(data.data)
                        }
                        UserData.provider = data.data.provider ?: "GUEST"
                        if (UserData.provider == "GUEST") {
                            UserData.nickname = "GUEST"
                        } else {
                            UserData.nickname = data.data.nickname ?: "GUEST"
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getUserProfileUseCase") }
            .launchIn(viewModelScope)
    }

    private fun fakeApiCall() {
        _reviews.update {
            List(10) {
                TempReviewData(
                    "우진이네 해장국",
                    if(Random.nextBoolean()) "https://picsum.photos/100" else null,
                    Date(),
                    it
                )
            }
        }
        _notices.update {
            List(8) {
                TempNoticeData(
                    Random.nextInt(1,3),
                    "${it + 1}월 1주차 공지 or 개편",
                    SimpleDateFormat("yyyy.MM.dd").parse("2024.0${it + 1}.01")!!
                )
            }
        }
    }
}
package com.jeju.nanaland.ui.profileupdate

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.usecase.member.DuplicateNicknameUseCase
import com.jeju.nanaland.domain.usecase.member.UpdateUserProfileUseCase
import com.jeju.nanaland.globalvalue.constant.INTRODUCTION_CONSTRAINT
import com.jeju.nanaland.globalvalue.constant.NICKNAME_CONSTRAINT
import com.jeju.nanaland.globalvalue.constant.nicknameRegex
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateUserProfileUseCase,
    private val duplicateNicknameUseCase: DuplicateNicknameUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _inputNickname = MutableStateFlow("")
    val inputNickname = _inputNickname.asStateFlow()
    private val _inputIntroduction = MutableStateFlow("")
    val inputIntroduction = _inputIntroduction.asStateFlow()
    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri: StateFlow<String?> = _imageUri

    private val _errorNickname = MutableStateFlow<Int?>(null)
    val errorNickname: StateFlow<Int?> = _errorNickname
    private val _errorIntro = MutableStateFlow<Int?>(null)
    val errorIntro: StateFlow<Int?> = _errorIntro

    init {
        updateProfileImageUri(Uri.parse(savedStateHandle["profileImageUri"]!!))
        updateInputNickname(savedStateHandle["nickname"]!!)
        updateInputIntroduction(savedStateHandle["introduction"]!!)

        @Suppress("OPT_IN_USAGE")
        inputNickname
            .debounce(600)
            .distinctUntilChanged()
            .onEach {
                if(!nicknameIsError())
                    nicknameIsErrorByApi()
            }
            .launchIn(viewModelScope)

        inputIntroduction
            .onEach {
                introIsError()
            }
            .launchIn(viewModelScope)
    }

    fun updateInputNickname(nickname: String) {
        _inputNickname.update { nickname }
    }

    fun updateInputIntroduction(introduction: String) {
        _inputIntroduction.update { introduction }
    }

    fun updateProfileImageUri(uri: Uri) {
        _imageUri.update { uri.toString() }
    }

    @SuppressLint("Recycle", "Range")
    fun updateProfile(image: UriRequestBody?, moveToBackScreen: () -> Unit) {
        val requestData = UpdateUserProfileRequest(
            nickname = _inputNickname.value,
            description = _inputIntroduction.value
        )

        updateProfileUseCase(requestData, image)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    moveToBackScreen()
                }.onError { code, message ->
                    when (code) {
                        409 -> {
                            _errorNickname.update { R.string.sign_up_profile_setting_warning2 }
                        }
                    }
                }.onException {

                }
            }
            .launchIn(viewModelScope)
    }

    private fun nicknameIsError(): Boolean {
        return if (_inputNickname.value.length > NICKNAME_CONSTRAINT) {
            _errorNickname.update { R.string.sign_up_profile_setting_warning1 }
            true
        } else if (!_inputNickname.value.matches(nicknameRegex)) {
            _errorNickname.update { R.string.sign_up_profile_setting_warning3 }
            true
        } else {
            false
        }
    }

    private suspend fun nicknameIsErrorByApi(): Boolean {
        return if(duplicateNicknameUseCase(_inputNickname.value) is NetworkResult.Success) {
            _errorNickname.update { null }
            false
        } else {
            _errorNickname.update { R.string.sign_up_profile_setting_warning2 }
            true
        }
    }

    private fun introIsError(): Boolean {
        return if (_inputIntroduction.value.length > INTRODUCTION_CONSTRAINT) {
            _errorIntro.update { R.string.profile_update_screen_warning }
            true
        } else {
            _errorIntro.update { null }
            false
        }
    }
}
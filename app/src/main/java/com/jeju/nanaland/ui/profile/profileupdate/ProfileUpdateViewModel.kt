package com.jeju.nanaland.ui.profile.profileupdate

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.file.FileCategory
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.usecase.file.PutFileUseCase
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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateUserProfileUseCase,
    private val duplicateNicknameUseCase: DuplicateNicknameUseCase,
    private val putFileUseCase: PutFileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val stateHandle: ROUTE.Main.Profile.Update = savedStateHandle.toRoute()

    private val _inputNickname = MutableStateFlow(stateHandle.nickname)
    val inputNickname = _inputNickname.asStateFlow()
    private val _inputIntroduction = MutableStateFlow(stateHandle.introduction)
    val inputIntroduction = _inputIntroduction.asStateFlow()
    private val _imageUri = MutableStateFlow(Uri.parse(stateHandle.profileImageUri).toString())
    val imageUri: StateFlow<String> = _imageUri

    private val _errorNickname = MutableStateFlow<Int?>(null)
    val errorNickname: StateFlow<Int?> = _errorNickname

    val errorIntro: SharedFlow<Int?> = inputIntroduction
        .map {
            if (_inputIntroduction.value.length > INTRODUCTION_CONSTRAINT) {
                R.string.profile_update_screen_warning
            } else {
                null
            }
        }.shareIn(viewModelScope, SharingStarted.Eagerly)

    init {
        viewModelScope.launch {
            @Suppress("OPT_IN_USAGE")
            inputNickname
                .debounce(600)
                .distinctUntilChanged()
                .collectLatest {
                    if(it == stateHandle.nickname)
                        _errorNickname.update { null }
                    else
                        _errorNickname.update { _ -> checkNickname(it) }
                }
        }
    }

    fun updateInputNickname(nickname: String) {
        _inputNickname.update { nickname }
    }

    fun updateInputIntroduction(introduction: String) {
        _inputIntroduction.update { introduction }
    }

    fun updateProfileImageUri(uri: String) {
        _imageUri.update { uri }
    }

    @SuppressLint("Recycle", "Range")
    fun updateProfile(image:String?, moveToBackScreen: () -> Unit) {
        val requestData = UpdateUserProfileRequest(
            nickname = _inputNickname.value,
            description = _inputIntroduction.value
        )

        viewModelScope.launch {
            val fileKey = image?.toIntOrNull()?.let {
                intToAPIString(it)
            } ?: image?.let {
                putFileUseCase(it, FileCategory.Profile)
            }

            updateProfileUseCase(requestData, fileKey)
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
    }

    private suspend fun checkNickname(nickname: String): Int? {
        return if (nickname.length > NICKNAME_CONSTRAINT)
            R.string.sign_up_profile_setting_warning1
        else if (!nickname.matches(nicknameRegex))
            R.string.sign_up_profile_setting_warning3
        else  if(duplicateNicknameUseCase(nickname) !is NetworkResult.Success)
            R.string.sign_up_profile_setting_warning2
        else
            null
    }


    private fun intToAPIString(index: Int): String = "default/" + when(index) {
        0 -> "Gray"
        1 -> "LightGray"
        2 -> "DeepBlue"
        3 -> "LightPurple"
        else -> throw Exception()
    } + ".png"
}
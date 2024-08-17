package com.jeju.nanaland.ui.signup

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.member.ConsentItem
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.domain.usecase.auth.SignUpUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.member.DuplicateNicknameUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetValueUseCase
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import com.jeju.nanaland.globalvalue.constant.NICKNAME_CONSTRAINT
import com.jeju.nanaland.globalvalue.constant.nicknameRegex
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getValueUseCase: GetValueUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val duplicateNicknameUseCase: DuplicateNicknameUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _inputNickname = MutableStateFlow("")
    val inputNickname = _inputNickname.asStateFlow()
    private val _profileImageUri = MutableStateFlow<String?>(null)
    val profileImageUri = _profileImageUri.asStateFlow()

    private val _errorNickname = MutableStateFlow<Int?>(null)
    val errorNickname: StateFlow<Int?> = _errorNickname

    init {
        @Suppress("OPT_IN_USAGE")
        inputNickname
            .debounce(600)
            .distinctUntilChanged()
            .onEach {
                if(!nicknameIsError())
                    nicknameIsErrorByApi()
            }
            .launchIn(viewModelScope)
    }
    fun updateInputNickname(nickname: String) {
        _inputNickname.update { nickname }
    }

    fun updateProfileImageUri(uri: Uri) {
        _profileImageUri.update { uri.toString() }
    }

    @SuppressLint("Range", "Recycle")
    fun signUp(
        provider: String,
        email: String,
        providerId: String,
        isPrivacyPolicyAgreed: Boolean,
        isMarketingPolicyAgreed: Boolean,
        isLocationPolicyAgreed: Boolean,
        moveToTypeTestingScreen: () -> Unit,
    ) {
        if (_inputNickname.value.length > 8 || _inputNickname.value.isEmpty()) return

        var locale = "ENGLISH"
        getValueUseCase(key = KEY_LANGUAGE)
            .onEach {
                locale = when (it) {
                    "en" -> "ENGLISH"
                    "zh" -> "CHINESE"
                    "ms" -> "MALAYSIA"
                    "ko" -> "KOREAN"
                    else -> "ENGLISH"
                }
            }
            .catch { LogUtil.e("flow Error", "getValueUseCase") }
            .launchIn(viewModelScope)

        var imageFile: File? = null
        if (_profileImageUri.value?.contains("content") == true) {
            val cursor = application.contentResolver.query(_profileImageUri.value!!.toUri(), null, null, null, null)
            cursor?.moveToNext()
            val path = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
            imageFile = path?.let { File(it) }
        }

        val requestData = SignUpRequest(
            consentItems = listOf(
                ConsentItem("TERMS_OF_USE", isPrivacyPolicyAgreed),
                ConsentItem("MARKETING", isMarketingPolicyAgreed),
                ConsentItem("LOCATION_SERVICE", isLocationPolicyAgreed)
            ),
            email = email,
            provider = provider,
            providerId = providerId,
            locale = locale,
            nickname = _inputNickname.value
        )

        signUpUseCase(requestData, imageFile)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        saveAccessTokenUseCase(it.accessToken ?: "")
                        saveRefreshTokenUseCase(it.refreshToken ?: "")
                        UserData.nickname = _inputNickname.value
                        UserData.provider = provider
                        moveToTypeTestingScreen()
                    }
                }.onError { code, message ->
                    when (code) {
                        409 -> {
                            _errorNickname.update { R.string.sign_up_profile_setting_warning2 }
                        }
                    }
                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "signUpUseCase") }
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
}
package com.jeju.nanaland.ui.signup

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.member.ConsentItem
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.domain.usecase.auth.SignUpUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.globalvalue.type.SignUpNicknameState
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.io.File
import java.util.Arrays
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()
    private val _nicknameState = MutableStateFlow(SignUpNicknameState.Idle)
    val nicknameState = _nicknameState.asStateFlow()
    private val _profileImageUri = MutableStateFlow<String?>(null)
    val profileImageUri: StateFlow<String?> = _profileImageUri

    fun updateNickname(nickname: String) {
        _nickname.update { nickname }
        if (_nickname.value.length <= 8) {
            _nicknameState.update { SignUpNicknameState.Idle }
        } else {
            _nicknameState.update { SignUpNicknameState.Invalid }
        }
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
        if (_nickname.value.length > 8) return

        var locale = "ENGLISH"
        getLanguageUseCase()
            .onEach {
                locale = when (it) {
                    "en" -> "ENGLISH"
                    "zh" -> "CHINESE"
                    "ms" -> "MALAYSIA"
                    "ko" -> "KOREAN"
                    else -> "ENGLISH"
                }
            }
            .catch { LogUtil.e("flow Error", "getLanguageUseCase") }
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
            nickname = _nickname.value
        )

        signUpUseCase(requestData, imageFile)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        saveAccessTokenUseCase(it.data.accessToken ?: "")
                        saveRefreshTokenUseCase(it.data.refreshToken ?: "")
                        UserData.nickname = _nickname.value
                        UserData.provider = provider
                        moveToTypeTestingScreen()
                    }
                }.onError { code, message ->
                    LogUtil.e("onError", "code: ${code}\nmessage: ${message}")
                    when (code) {
                        409 -> {
                            _nicknameState.update { SignUpNicknameState.Duplicated }
                        }
                    }
                }.onException {
                    LogUtil.e("onException", it.message + "\n" + Arrays.toString(it.stackTrace).replace(", ", "\n")
                        .replace("[", "").replace("]", ""))
                }
            }
            .catch { LogUtil.e("flow Error", "signUpUseCase") }
            .launchIn(viewModelScope)
    }
}
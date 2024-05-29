package com.jeju.nanaland.ui.profileupdate

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.usecase.member.UpdateUserProfileUseCase
import com.jeju.nanaland.globalvalue.type.InputNicknameState
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
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateUserProfileUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _inputNickname = MutableStateFlow("")
    val inputNickname = _inputNickname.asStateFlow()
    private val _inputNicknameState = MutableStateFlow(InputNicknameState.Idle)
    val inputNicknameState = _inputNicknameState.asStateFlow()
    private val _inputIntroduction = MutableStateFlow("")
    val inputIntroduction = _inputIntroduction.asStateFlow()
    private val _profileImageUri = MutableStateFlow<String?>(null)
    val profileImageUri: StateFlow<String?> = _profileImageUri

    fun updateInputNickname(nickname: String) {
        _inputNickname.update { nickname }
        if (_inputNickname.value.length <= 8) {
            _inputNicknameState.update { InputNicknameState.Idle }
        } else {
            _inputNicknameState.update { InputNicknameState.Invalid }
        }
    }

    fun updateInputIntroduction(introduction: String) {
        _inputIntroduction.update { introduction }
    }

    fun updateProfileImageUri(uri: Uri) {
        _profileImageUri.update { uri.toString() }
    }

    @SuppressLint("Recycle", "Range")
    fun updateProfile(moveToBackScreen: () -> Unit) {
        val requestData = UpdateUserProfileRequest(
            nickname = _inputNickname.value,
            description = _inputIntroduction.value
        )

        var imageFile: File? = null
        if (_profileImageUri.value?.contains("content") == true) {
            val cursor = application.contentResolver.query(_profileImageUri.value!!.toUri(), null, null, null, null)
            cursor?.moveToNext()
            val path = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
            imageFile = path?.let { File(it) }
        }

        updateProfileUseCase(requestData, imageFile)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {

                    }
                    moveToBackScreen()
                }.onError { code, message ->
                    when (code) {
                        409 -> {
                            _inputNicknameState.update { InputNicknameState.Duplicated }
                        }
                    }
                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "updateProfileUseCase") }
            .launchIn(viewModelScope)
    }
}
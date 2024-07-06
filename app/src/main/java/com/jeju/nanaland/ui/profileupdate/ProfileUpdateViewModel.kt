package com.jeju.nanaland.ui.profileupdate

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.usecase.member.UpdateUserProfileUseCase
import com.jeju.nanaland.globalvalue.constant.INTRODUCTION_CONSTRAINT
import com.jeju.nanaland.globalvalue.constant.NICKNAME_CONSTRAINT
import com.jeju.nanaland.globalvalue.type.InputIntroductionState
import com.jeju.nanaland.globalvalue.type.InputNicknameState
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.globalvalue.constant.nicknameRegex
import com.jeju.nanaland.util.file.copy
import com.jeju.nanaland.util.file.getFileExtension
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.io.File
import java.io.FileOutputStream
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
    private val _inputIntroductionState = MutableStateFlow(InputIntroductionState.Idle)
    val inputIntroductionState = _inputIntroductionState.asStateFlow()
    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri: StateFlow<String?> = _imageUri

    fun updateInputNickname(nickname: String) {
        _inputNickname.update { nickname }
        if (_inputNickname.value.length > NICKNAME_CONSTRAINT) {
            _inputNicknameState.update { InputNicknameState.TooLong }
        } else if (!_inputNickname.value.matches(nicknameRegex)) {
            _inputNicknameState.update { InputNicknameState.Invalid }
        } else {
            _inputNicknameState.update { InputNicknameState.Idle }
        }
    }

    fun updateInputIntroduction(introduction: String) {
        _inputIntroduction.update { introduction }
        if (_inputIntroduction.value.length > INTRODUCTION_CONSTRAINT) {
            _inputIntroductionState.update { InputIntroductionState.Invalid }
        } else {
            _inputIntroductionState.update { InputIntroductionState.Idle }
        }
    }

    fun updateProfileImageUri(uri: Uri) {
        _imageUri.update { uri.toString() }
    }

    @SuppressLint("Recycle", "Range")
    fun updateProfile(moveToBackScreen: () -> Unit) {
        val requestData = UpdateUserProfileRequest(
            nickname = _inputNickname.value,
            description = _inputIntroduction.value
        )

        val fileExtension = getFileExtension(application, _imageUri.value!!.toUri())
        val fileName = "temporary_file" + if (fileExtension != null) ".$fileExtension" else ""

        val imageFile = File(application.cacheDir, fileName)
        imageFile.createNewFile()

        try {
            val oStream = FileOutputStream(imageFile)
            val inputStream = application.contentResolver.openInputStream(_imageUri.value!!.toUri())

            inputStream?.let {
                copy(inputStream, oStream)
            }

            oStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
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
package com.jeju.nanaland.ui.main.mypage.profilemodification

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.jeju.nanaland.globalvalue.type.SignUpNicknameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileModificationViewModel @Inject constructor(

) : ViewModel() {

    private val _inputNickname = MutableStateFlow("")
    val inputNickname = _inputNickname.asStateFlow()
    private val _nicknameState = MutableStateFlow(SignUpNicknameState.Idle)
    val nicknameState = _nicknameState.asStateFlow()
    private val _inputIntroduction = MutableStateFlow("")
    val inputIntroduction = _inputIntroduction.asStateFlow()
    private val _profileImageUri = MutableStateFlow<String?>(null)
    val profileImageUri: StateFlow<String?> = _profileImageUri

    fun updateInputNickname(nickname: String) {
        _inputNickname.update { nickname }
    }

    fun updateInputIntroduction(introduction: String) {
        _inputIntroduction.update { introduction }
    }

    fun updateProfileImageUri(uri: Uri) {
        _profileImageUri.update { uri.toString() }
    }

    fun modifyProfile() {

    }
}
package com.jeju.nanaland.ui.infomodification

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.file.FileCategory
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.domain.usecase.file.PutFileUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.report.InformationModificationProposalUseCase
import com.jeju.nanaland.globalvalue.constant.emailRegex
import com.jeju.nanaland.globalvalue.type.InputEmailState
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoModificationProposalViewModel @Inject constructor(
    private val infoModificationUseCase: InformationModificationProposalUseCase,
    getUserProfileUseCase: GetUserProfileUseCase,
    private val putFileUseCase: PutFileUseCase,
) : ViewModel() {

    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri = _imageUri.asStateFlow()
    private val _inputDescription = MutableStateFlow("")
    val inputDescription = _inputDescription.asStateFlow()
    private val _inputEmail = MutableStateFlow("")
    val inputEmail = _inputEmail.asStateFlow()
    private val _inputEmailState = MutableStateFlow(InputEmailState.Idle)
    val inputEmailState = _inputEmailState.asStateFlow()
    val selectedImageList = mutableStateListOf<String>()

    private val _callState = MutableStateFlow<UiState<Unit>?>(null)
    val callState = _callState.asStateFlow()

    init {
        getUserProfileUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _inputEmail.update { data.email }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun updateImageUri(uri: Uri) {
        _imageUri.update { uri.toString() }
    }

    fun updateInputDescription(description: String) {
        _inputDescription.update { description }
    }

    fun updateInputEmail(email: String) {
        _inputEmail.update { email }
        if (email.matches(emailRegex)) {
            _inputEmailState.update { InputEmailState.Idle }
        } else {
            _inputEmailState.update { InputEmailState.Invalid }
        }
    }

    fun changeImage(arg: List<String>) {
        selectedImageList.clear()
        selectedImageList.addAll(arg)
    }

    @SuppressLint("Range", "Recycle")
    fun sendReport(postId: Int, fixType: String, category: String) {
        viewModelScope.launch {
            _callState.update { UiState.Loading }

            val images = selectedImageList.map {
                putFileUseCase(it, FileCategory.InfoFixReport)
            }
            val requestData = InformationModificationProposalRequest(
                postId = postId,
                fixType = fixType,
                category = category,
                content = _inputDescription.value,
                email = _inputEmail.value,
                images = images
            )


            infoModificationUseCase(requestData)
                .onEach { networkResult ->
                    networkResult.onSuccess { code, message, data ->
                        _callState.update { UiState.Success(Unit) }
                    }.onError { code, message ->
                        _callState.update { UiState.Failure("") }
                    }.onException {
                        _callState.update { UiState.Failure("") }
                    }
                }
                .catch {
                    _callState.update { UiState.Failure("") }
                    LogUtil.e("flow error", "infoModificationUseCase")
                }
                .launchIn(viewModelScope)
        }
    }
    fun setCallStateNull(){
        _callState.update { null }
    }
}
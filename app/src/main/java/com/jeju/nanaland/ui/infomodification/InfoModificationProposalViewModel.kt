package com.jeju.nanaland.ui.infomodification

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.report.InformationModificationProposalUseCase
import com.jeju.nanaland.globalvalue.constant.emailRegex
import com.jeju.nanaland.globalvalue.type.InputEmailState
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InfoModificationProposalViewModel @Inject constructor(
    private val infoModificationUseCase: InformationModificationProposalUseCase,
    getUserProfileUseCase: GetUserProfileUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri = _imageUri.asStateFlow()
    private val _inputDescription = MutableStateFlow("")
    val inputDescription = _inputDescription.asStateFlow()
    private val _inputEmail = MutableStateFlow("")
    val inputEmail = _inputEmail.asStateFlow()
    private val _inputEmailState = MutableStateFlow(InputEmailState.Idle)
    val inputEmailState = _inputEmailState.asStateFlow()
    val selectedImageList = mutableStateListOf<String>()

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
    fun sendReport(postId: Int, fixType: String, category: String, moveToCompleteScreen: () -> Unit) {
        val requestData = InformationModificationProposalRequest(
            postId = postId,
            fixType = fixType,
            category = category,
            content = _inputDescription.value,
            email = _inputEmail.value
        )

//        val fileExtension = getFileExtension(application, _imageUri.value!!.toUri())
//        val fileName = "temporary_file" + if (fileExtension != null) ".$fileExtension" else ""
//
//        val imageFile = File(application.cacheDir, fileName)
//        imageFile.createNewFile()
//
//        try {
//            val oStream = FileOutputStream(imageFile)
//            val inputStream = application.contentResolver.openInputStream(_imageUri.value!!.toUri())
//
//            inputStream?.let {
//                copy(inputStream, oStream)
//            }
//
//            oStream.flush()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

//        var imageFile: File? = null
//        if (_imageUri.value?.contains("content") == true) {
//            val cursor = application.contentResolver.query(_imageUri.value!!.toUri(), null, null, null, null)
//            cursor?.moveToNext()
//            val path = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
//            imageFile = path?.let { File(it) }
//        }

        infoModificationUseCase(requestData, selectedImageList.map { UriRequestBody(application, it.toUri()) })
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    moveToCompleteScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "infoModificationUseCase") }
            .launchIn(viewModelScope)
    }
}
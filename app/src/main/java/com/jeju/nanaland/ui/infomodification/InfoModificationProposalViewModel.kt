package com.jeju.nanaland.ui.infomodification

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.domain.usecase.report.InformationModificationProposalUseCase
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
import java.io.File
import javax.inject.Inject

@HiltViewModel
class InfoModificationProposalViewModel @Inject constructor(
    private val infoModificationUseCase: InformationModificationProposalUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri = _imageUri.asStateFlow()
    private val _inputDescription = MutableStateFlow("")
    val inputDescription = _inputDescription.asStateFlow()
    private val _inputEmail = MutableStateFlow("")
    val inputEmail = _inputEmail.asStateFlow()

    fun updateImageUri(uri: Uri) {
        _imageUri.update { uri.toString() }
    }

    fun updateInputDescription(description: String) {
        _inputDescription.update { description }
    }

    fun updateInputEmail(email: String) {
        _inputEmail.update { email }
    }

    @SuppressLint("Range", "Recycle")
    fun sendReport(postId: Long, fixType: String, category: String, moveToCompleteScreen: () -> Unit) {
        val requestData = InformationModificationProposalRequest(
            postId = postId,
            fixType = fixType,
            category = category,
            content = _inputDescription.value,
            email = _inputEmail.value
        )

        var imageFile: File? = null
        if (_imageUri.value?.contains("content") == true) {
            val cursor = application.contentResolver.query(_imageUri.value!!.toUri(), null, null, null, null)
            cursor?.moveToNext()
            val path = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
            imageFile = path?.let { File(it) }
        }

        infoModificationUseCase(requestData, imageFile)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    moveToCompleteScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "infoModificationUseCase") }
            .launchIn(viewModelScope)
    }
}
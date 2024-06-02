package com.jeju.nanaland.ui.infomodification

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenDescription
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenHeading
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenImagePreview
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenTextField
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenBottomButton
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun InformationModificationProposalWritingScreen(
    postId: Long,
    fixType: String,
    category: String,
    moveToBackScreen: () -> Unit,
    moveToCompleteScreen: () -> Unit,
    viewModel: InfoModificationProposalViewModel = hiltViewModel()
) {
    val imageUri = viewModel.imageUri.collectAsState().value
    val inputDescription = viewModel.inputDescription.collectAsState().value
    val inputEmail = viewModel.inputEmail.collectAsState().value
    InformationModificationProposalWritingScreen(
        imageUri = imageUri,
        inputDescription = inputDescription,
        inputEmail = inputEmail,
        updateImageUri = viewModel::updateImageUri,
        updateInputDescription = viewModel::updateInputDescription,
        updateInputEmail = viewModel::updateInputEmail,
        sendReport = {
            viewModel.sendReport(
                postId = postId,
                fixType = fixType,
                category = category,
                moveToCompleteScreen = it
            )
        },
        moveToBackScreen = moveToBackScreen,
        moveToCompleteScreen = moveToCompleteScreen,
        isContent = true
    )
}

@Composable
private fun InformationModificationProposalWritingScreen(
    imageUri: String?,
    inputDescription: String,
    inputEmail: String,
    updateImageUri: (Uri) -> Unit,
    updateInputDescription: (String) -> Unit,
    updateInputEmail: (String) -> Unit,
    sendReport: (() -> Unit) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToCompleteScreen: () -> Unit,
    isContent: Boolean
) {
    val focusManager = LocalFocusManager.current
    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            Log.e("PickImage", "success")
            Log.e("PickImage", "${uri}")
            uri?.let {
                updateImageUri(uri)
            }
        }

    CustomSurface {
        CustomTopBar(
            title = "정보 수정 제안",
            onBackButtonClicked = moveToBackScreen
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = remember { scrollableVerticalArrangement }
        ) {
            item {
                InfoModificationProposalWritingScreenImagePreview(
                    imageUri = imageUri,
                    onClick = {
                        focusManager.clearFocus()
                        takePhotoFromAlbumLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                )

                Spacer(Modifier.height(24.dp))

                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    InfoModificationProposalWritingScreenHeading(text = "정보 수정 제안")

                    Spacer(Modifier.height(8.dp))

                    InfoModificationProposalWritingScreenTextField(
                        height = 120,
                        hint = "수정 요청하신 항목의 상세 내용이나 그 외 기타 사항이 있으시면 의견을 남겨주세요.",
                        inputText = inputDescription,
                        onValueChange = updateInputDescription
                    )

                    Spacer(Modifier.height(48.dp))

                    InfoModificationProposalWritingScreenHeading(text = "이메일")

                    Spacer(Modifier.height(4.dp))

                    InfoModificationProposalWritingScreenDescription()

                    Spacer(Modifier.height(8.dp))

                    InfoModificationProposalWritingScreenTextField(
                        height = 48,
                        hint = "example@naver.com",
                        inputText = inputEmail,
                        onValueChange = updateInputEmail
                    )
                }

                Spacer(Modifier.height(40.dp))
            }

            item {
                InfoModificationProposalWritingScreenBottomButton(
                    isActivated = (imageUri != null && inputDescription.isNotEmpty() && inputEmail.isNotEmpty()),
                    onClick = { sendReport(moveToCompleteScreen) }
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
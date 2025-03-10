package com.jeju.nanaland.ui.infomodification

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.InputEmailState
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.UploadImages
import com.jeju.nanaland.ui.component.common.dialog.SubmitLoadingDialog
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenBottomButton
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenDescription
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenHeading
import com.jeju.nanaland.ui.component.infomodification.writing.InfoModificationProposalWritingScreenTextField
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun InformationModificationProposalWritingScreen(
    postId: Int,
    fixType: String,
    category: String,
    moveToBackScreen: () -> Unit,
    moveToCompleteScreen: () -> Unit,
    viewModel: InfoModificationProposalViewModel = hiltViewModel()
) {
    val imageUri = viewModel.imageUri.collectAsState().value
    val inputDescription = viewModel.inputDescription.collectAsState().value
    val inputEmail = viewModel.inputEmail.collectAsState().value
    val inputEmailState = viewModel.inputEmailState.collectAsState().value
    val selectedImageList = viewModel.selectedImageList
    val callState = viewModel.callState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    callState.value?.let {
        when (it) {
            is UiState.Loading -> {
                SubmitLoadingDialog(
                    getString(R.string.loading_wait_text_desc4),
                )
            }
            is UiState.Success -> {
                moveToCompleteScreen()
            }
            is UiState.Failure -> {
                viewModel.setCallStateNull()
                Toast.makeText(context, getString(R.string.common_인터넷_문제), Toast.LENGTH_LONG).show()
            }
        }
    }

    InformationModificationProposalWritingScreen(
        imageUri = imageUri,
        inputDescription = inputDescription,
        inputEmail = inputEmail,
        inputEmailState = inputEmailState,
        updateImageUri = viewModel::updateImageUri,
        updateInputDescription = viewModel::updateInputDescription,
        updateInputEmail = viewModel::updateInputEmail,
        sendReport = {
            viewModel.sendReport(
                postId = postId,
                fixType = fixType,
                category = category,
            )
        },
        moveToBackScreen = moveToBackScreen,
        selectedImageList = selectedImageList,
        onChangeImages = viewModel::changeImage,
        isContent = true
    )
}

@Composable
private fun InformationModificationProposalWritingScreen(
    imageUri: String?,
    inputDescription: String,
    inputEmail: String,
    inputEmailState: InputEmailState,
    updateImageUri: (Uri) -> Unit,
    updateInputDescription: (String) -> Unit,
    updateInputEmail: (String) -> Unit,
    sendReport: () -> Unit,
    moveToBackScreen: () -> Unit,
    selectedImageList: SnapshotStateList<String>,
    onChangeImages: (List<String>) -> Unit,
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
        TopBarCommon(
            title = getString(R.string.info_modification_proposal_정보_수정_제안2),
            onBackButtonClicked = moveToBackScreen
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = remember { scrollableVerticalArrangement }
        ) {
            item {
//                InfoModificationProposalWritingScreenImagePreview(
//                    imageUri = imageUri,
//                    onClick = {
//                        focusManager.clearFocus()
//                        takePhotoFromAlbumLauncher.launch(
//                            PickVisualMediaRequest(
//                                ActivityResultContracts.PickVisualMedia.ImageOnly
//                            )
//                        )
//                    }
//                )
                Column {
                    Column(Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp)) {
                        InfoModificationProposalWritingScreenHeading(text = getString(R.string.info_modification_proposal_heading3))

                        Spacer(Modifier.height(8.dp))

                        InfoModificationProposalWritingScreenTextField(
                            line = 4,
                            innerPadding = PaddingValues(16.dp),
                            hint = getString(R.string.info_modification_proposal_hint1),
                            inputText = inputDescription,
                            onValueChange = updateInputDescription
                        )

                        Spacer(Modifier.height(48.dp))

                        InfoModificationProposalWritingScreenHeading(text = getString(R.string.common_이메일))

                        Spacer(Modifier.height(4.dp))

                        InfoModificationProposalWritingScreenDescription()

                        Spacer(Modifier.height(8.dp))

                        InfoModificationProposalWritingScreenTextField(
                            line = 1,
                            innerPadding = PaddingValues(horizontal = 16.dp, vertical = 13.dp),
                            hint = getString(R.string.info_modification_proposal_hint2),
                            inputText = inputEmail,
                            onValueChange = updateInputEmail,
                            isValid = inputEmailState == InputEmailState.Idle,
                        )

                        Spacer(Modifier.height(48.dp))

                        Text(
                            text = getString(R.string.common_사진_동영상),
                            color = getColor().black,
                            style = title02Bold
                        )

                        Spacer(Modifier.height(8.dp))
                    }
                }

                UploadImages(
                    images = selectedImageList.toList(),
                    onChangeImages = onChangeImages,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(40.dp))
            }

            item {
                InfoModificationProposalWritingScreenBottomButton(
                    isActivated = (inputDescription.isNotEmpty() && inputEmail.isNotEmpty() && inputEmailState == InputEmailState.Idle),
                    onClick = sendReport
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
package com.jeju.nanaland.ui.profileupdate

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.globalvalue.constant.INTRODUCTION_CONSTRAINT
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.DialogCommon
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenBottomButton
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenIntroductionTextField
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenNicknameText
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenProfileContent
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenCharacterCount
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenTextField
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun ProfileUpdateScreen(
    profileImageUri: String,
    nickname: String,
    introduction: String,
    moveToBackScreen: () -> Unit,
    viewModel: ProfileUpdateViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val inputNickname = viewModel.inputNickname.collectAsState().value
    val inputNicknameState = viewModel.errorNickname.collectAsState().value
    val inputIntroduction = viewModel.inputIntroduction.collectAsState().value
    val inputIntroductionState = viewModel.errorIntro.collectAsState().value
    val inputProfileImageUri = viewModel.imageUri.collectAsState().value
    ProfileUpdateScreen(
        prevNickname = nickname,
        prevIntroduction = introduction,
        prevProfileImageUri = inputProfileImageUri,
        inputNickname = inputNickname,
        updateInputNickname = viewModel::updateInputNickname,
        inputNicknameError = inputNicknameState,
        inputIntroduction = inputIntroduction,
        inputIntroductionError = inputIntroductionState,
        updateInputIntroduction = viewModel::updateInputIntroduction,
        inputProfileImageUri = inputProfileImageUri,
        updateProfileImageUri = viewModel::updateProfileImageUri,
        updateProfile = {
            val image = if(profileImageUri == inputProfileImageUri) null
                else UriRequestBody(context, Uri.parse(inputProfileImageUri))
            viewModel.updateProfile(image = image, moveToBackScreen = moveToBackScreen)
        },
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ProfileUpdateScreen(
    prevNickname: String,
    prevIntroduction: String,
    prevProfileImageUri: String?,
    inputNickname: String,
    updateInputNickname: (String) -> Unit,
    inputNicknameError: Int?,
    inputIntroduction: String,
    inputIntroductionError: Int?,
    updateInputIntroduction: (String) -> Unit,
    inputProfileImageUri: String?,
    updateProfileImageUri: (Uri) -> Unit,
    updateProfile: () -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val isWarningDialogShowing = remember { mutableStateOf(false) }
    BackHandler {
        if (prevProfileImageUri != inputProfileImageUri || prevNickname != inputNickname || prevIntroduction != inputIntroduction) {
            isWarningDialogShowing.value = true
        } else {
            moveToBackScreen()
        }
    }
    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            Log.e("PickImage", "success")
            Log.e("PickImage", "${uri}")
            uri?.let {
                updateProfileImageUri(uri)
            }
        }

    CustomSurface {
        CustomTopBar(
            title = getString(R.string.profile_update_screen_프로필_수정),
            onBackButtonClicked = {
                if (prevNickname != inputNickname || prevIntroduction != inputIntroduction) {
                    LogUtil.e("", prevNickname + "," + inputNickname + "," + prevIntroduction + "," + inputIntroduction)
                    isWarningDialogShowing.value = true
                } else {
                    moveToBackScreen()
                }
            }
        )

        Spacer(Modifier.height(40.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = remember { scrollableVerticalArrangement }
        ) {
            item {
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    ProfileUpdateScreenProfileContent(
                        imageUri = inputProfileImageUri,
                        onClick = {
                            takePhotoFromAlbumLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    )

                    Spacer(Modifier.height(48.dp))

                    Row {
                        ProfileUpdateScreenNicknameText()

                        Spacer(Modifier.weight(1f))

                        SignUpScreenCharacterCount(
                            count = inputNickname.length,
                            isError = inputNicknameError != null
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    SignUpScreenTextField(
                        inputText = inputNickname,
                        onValueChange = updateInputNickname,
                        error = inputNicknameError
                    )

                    Spacer(Modifier.height(80.dp))

                    Row {
                        Text(
                            text = getString(R.string.common_소개),
                            color = getColor().black,
                            style = bodyBold
                        )

                        Spacer(Modifier.weight(1f))

                        SignUpScreenCharacterCount(
                            count = inputIntroduction.length,
                            maxCount = INTRODUCTION_CONSTRAINT,
                            isError = inputIntroductionError != null
                        )
                    }

                    Spacer(Modifier.height(4.dp))

                    ProfileUpdateScreenIntroductionTextField(
                        inputText = inputIntroduction,
                        onValueChange = updateInputIntroduction,
                        error = inputIntroductionError
                    )

                    Spacer(Modifier.height(40.dp))
                }
            }
            item {
                ProfileUpdateScreenBottomButton(
                    isActivated = inputNickname.isNotEmpty() && inputNicknameError == null && inputIntroductionError == null,
                    onClick = updateProfile
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }

    if (isWarningDialogShowing.value) {
        DialogCommon(
            title = getString(R.string.review_write_cancel_dialog_title),
            subTitle = getString(R.string.review_write_cancel_dialog_subtitle),
            onDismissRequest = { isWarningDialogShowing.value = false },
            onPositive = moveToBackScreen,
            onNegative = { isWarningDialogShowing.value = false }
        )
    }
}
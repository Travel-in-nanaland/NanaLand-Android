package com.jeju.nanaland.ui.profileupdate

import android.net.Uri
import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.globalvalue.type.InputNicknameState
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.mypage.MyPageScreenIntroductionText
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenBottomButton
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenIntroductionTextField
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenNicknameText
import com.jeju.nanaland.ui.component.profileupdate.ProfileUpdateScreenProfileContent
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenCharacterCount
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenTextField
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun ProfileUpdateScreen(
    profileImageUri: String,
    nickname: String,
    introduction: String,
    moveToBackScreen: () -> Unit,
    viewModel: ProfileUpdateViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.updateProfileImageUri(Uri.parse(profileImageUri))
        viewModel.updateInputNickname(nickname)
        viewModel.updateInputIntroduction(introduction)
    }
    val inputNickname = viewModel.inputNickname.collectAsState().value
    val inputNicknameState = viewModel.inputNicknameState.collectAsState().value
    val inputIntroduction = viewModel.inputIntroduction.collectAsState().value
    val profileImageUri = viewModel.profileImageUri.collectAsState().value
    ProfileUpdateScreen(
        inputNickname = inputNickname,
        updateInputNickname = viewModel::updateInputNickname,
        inputNicknameState = inputNicknameState,
        inputIntroduction = inputIntroduction,
        updateInputIntroduction = viewModel::updateInputIntroduction,
        profileImageUri = profileImageUri,
        updateProfileImageUri = viewModel::updateProfileImageUri,
        updateProfile = viewModel::updateProfile,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ProfileUpdateScreen(
    inputNickname: String,
    updateInputNickname: (String) -> Unit,
    inputNicknameState: InputNicknameState,
    inputIntroduction: String,
    updateInputIntroduction: (String) -> Unit,
    profileImageUri: String?,
    updateProfileImageUri: (Uri) -> Unit,
    updateProfile: (() -> Unit) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
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
            title = "프로필 수정",
            onBackButtonClicked = {
                moveToBackScreen()
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
                        imageUri = profileImageUri,
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

                        SignUpScreenCharacterCount(count = inputNickname.length)
                    }

                    Spacer(Modifier.height(8.dp))

                    SignUpScreenTextField(
                        inputText = inputNickname,
                        onValueChange = updateInputNickname,
                        inputState = inputNicknameState
                    )

                    Spacer(Modifier.height(80.dp))

                    MyPageScreenIntroductionText()

                    Spacer(Modifier.height(4.dp))

                    ProfileUpdateScreenIntroductionTextField(
                        inputText = inputIntroduction,
                        onValueChange = updateInputIntroduction
                    )

                    Spacer(Modifier.height(40.dp))
                }
            }
            item {
                ProfileUpdateScreenBottomButton(
                    isActivated = inputNickname.isNotEmpty() && inputNicknameState == InputNicknameState.Idle,
                    onClick = {
                        updateProfile(moveToBackScreen)
                    }
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
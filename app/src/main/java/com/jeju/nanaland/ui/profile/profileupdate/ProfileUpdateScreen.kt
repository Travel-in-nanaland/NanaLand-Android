package com.jeju.nanaland.ui.profile.profileupdate

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.INTRODUCTION_CONSTRAINT
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetSelectDialog
import com.jeju.nanaland.ui.component.common.dialog.DialogCommon
import com.jeju.nanaland.ui.component.common.dialog.DialogCommonType
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenCharacterCount
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenTextField
import com.jeju.nanaland.ui.profile.profileupdate.component.ProfileUpdateScreenBottomButton
import com.jeju.nanaland.ui.profile.profileupdate.component.ProfileUpdateScreenIntroductionTextField
import com.jeju.nanaland.ui.profile.profileupdate.component.ProfileUpdateScreenNicknameText
import com.jeju.nanaland.ui.profile.profileupdate.component.ProfileUpdateScreenProfileContent
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement
import kotlin.random.Random

private fun intToDrawbleId(index: Int): Int = when(index) {
    0 -> R.drawable.img_default_profile_gray
    1 -> R.drawable.img_default_profile_light_gray
    2 -> R.drawable.img_default_profile_deep_blue
    3 -> R.drawable.img_default_profile_light_purple
    else -> throw Exception()
}

@Composable
fun ProfileUpdateScreen(
    profileImageUri: String,
    nickname: String,
    introduction: String,
    moveToBackScreen: () -> Unit,
    viewModel: ProfileUpdateViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val inputNickname = viewModel.inputNickname.collectAsState()
    val inputNicknameState = viewModel.errorNickname.collectAsState()
    val inputIntroduction = viewModel.inputIntroduction.collectAsState()
    val inputIntroductionState = viewModel.errorIntro.collectAsState(null)
    val inputProfileImageUri = viewModel.imageUri.collectAsState()
    ProfileUpdateScreen(
        prevNickname = nickname,
        prevIntroduction = introduction,
        prevProfileImageUri = profileImageUri,
        inputNickname = inputNickname.value,
        updateInputNickname = viewModel::updateInputNickname,
        inputNicknameError = inputNicknameState.value,
        inputIntroduction = inputIntroduction.value,
        inputIntroductionError = inputIntroductionState.value,
        updateInputIntroduction = viewModel::updateInputIntroduction,
        inputProfileImageUri = inputProfileImageUri.value,
        updateProfileImageUri = viewModel::updateProfileImageUri,
        updateProfile = {
            val image = if(profileImageUri == inputProfileImageUri.value) null
                else inputProfileImageUri.value
            viewModel.updateProfile(image = image, moveToBackScreen = moveToBackScreen)
        },
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
    updateProfileImageUri: (String) -> Unit,
    updateProfile: () -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val isWarningDialogShowing = remember { mutableStateOf(false) }
    var isProfileImageDialogShowing by remember { mutableStateOf(false) }

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
                updateProfileImageUri(uri.toString())
            }
        }

    CustomSurface {
        TopBarCommon(
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
                        imageUri = inputProfileImageUri?.toIntOrNull()?.run {
                            intToDrawbleId(this)
                        } ?: inputProfileImageUri,
                        onClick = { isProfileImageDialogShowing = true }
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
            DialogCommonType.Write,
            onDismiss = { isWarningDialogShowing.value = false },
            onYes = moveToBackScreen,
        )
    }
    if(isProfileImageDialogShowing) {
        BottomSheetSelectDialog(
            onDismiss = { isProfileImageDialogShowing = false },
            items = arrayOf(
                getString(R.string.profile_update_screen_select_image_album) to {
                    isProfileImageDialogShowing = false
                    takePhotoFromAlbumLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
                getString(R.string.profile_update_screen_select_image_remove) to {
                    isProfileImageDialogShowing = false
                    updateProfileImageUri(Random.nextInt(4).toString())
                },
            ).sliceArray(
                0 until
                        if(inputProfileImageUri?.contains("/default/") == true) 1
                    else 2
            )
        )
    }
}
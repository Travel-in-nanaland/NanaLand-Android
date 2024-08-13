package com.jeju.nanaland.ui.signup

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenBottomButton
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenCharacterCount
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenGuideLine
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenPhotoPreview
import com.jeju.nanaland.ui.component.signup.profilesetting.SignUpScreenTextField
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun SignUpScreen(
    provider: String,
    email: String,
    providerId: String,
    isPrivacyPolicyAgreed: Boolean,
    isMarketingPolicyAgreed: Boolean,
    isLocationPolicyAgreed: Boolean,
    moveToTypeTestingScreen: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val inputNickname = viewModel.inputNickname.collectAsState().value
    val profileImageUri = viewModel.profileImageUri.collectAsState().value
    val inputNicknameState = viewModel.errorNickname.collectAsState().value
    SignUpScreen(
        inputNickname = inputNickname,
        updateInputNickname = viewModel::updateInputNickname,
        inputNicknameError = inputNicknameState,
        profileImageUri = profileImageUri,
        updateProfileImageUri = viewModel::updateProfileImageUri,
        signUp = {
            viewModel.signUp(
                provider = provider,
                email = email,
                providerId = providerId,
                isPrivacyPolicyAgreed = isPrivacyPolicyAgreed,
                isMarketingPolicyAgreed = isMarketingPolicyAgreed,
                isLocationPolicyAgreed = isLocationPolicyAgreed,
                moveToTypeTestingScreen = moveToTypeTestingScreen
            )
        },
        isContent = true
    )
}

@Composable
private fun SignUpScreen(
    inputNickname: String,
    updateInputNickname: (String) -> Unit,
    inputNicknameError: Int?,
    profileImageUri: String?,
    updateProfileImageUri: (Uri) -> Unit,
    signUp: () -> Unit,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = remember { scrollableVerticalArrangement }
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(80.dp))

                    SignUpScreenGuideLine()

                    Spacer(Modifier.height(32.dp))

                    SignUpScreenPhotoPreview(imageUri = profileImageUri) {
                        takePhotoFromAlbumLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }

                    Spacer(Modifier.height(48.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
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

                    Spacer(Modifier.height(40.dp))
                }
            }

            item {
                SignUpScreenBottomButton(
                    isActivated = inputNickname.isNotEmpty() && inputNicknameError == null,
                    onClick = signUp
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
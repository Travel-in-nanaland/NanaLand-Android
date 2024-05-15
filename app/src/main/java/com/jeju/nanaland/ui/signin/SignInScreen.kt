package com.jeju.nanaland.ui.signin

import android.R
import android.app.Activity
import android.content.IntentSender.SendIntentException
import android.service.credentials.BeginGetCredentialRequest
import android.service.credentials.GetCredentialRequest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.kakao.sdk.user.UserApiClient
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signin.SignInScreenGoogleLoginButton
import com.jeju.nanaland.ui.component.signin.SignInScreenGuestModeText
import com.jeju.nanaland.ui.component.signin.SignInScreenKakaoLoginButton
import com.jeju.nanaland.ui.component.signin.SignInScreenLogoImage
import com.jeju.nanaland.ui.component.signin.SignInScreenLogoText1
import com.jeju.nanaland.ui.component.signin.SignInScreenLogoText2
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.signin.AuthResultContract
import com.jeju.nanaland.util.signin.getGoogleSignInClient
import com.jeju.nanaland.util.ui.ScreenPreview
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel()
) {
    SignInScreen(
        isContent = true
    )
}

@Composable
private fun SignInScreen(
    isContent: Boolean
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val startForResult =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task: Task<GoogleSignInAccount>? ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    LogUtil.e("error", "${task?.exception}")
                } else {
                    LogUtil.e("success", "email: ${account.email}\n" +
                            "id: ${account.id}\n"
                    )
                    coroutineScope.launch {
                    }
                }
            } catch (e: ApiException) {
                LogUtil.e("error", "$e")
            }
        }
    CustomSurface {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            SignInScreenLogoImage()

            SignInScreenLogoText1()

            SignInScreenLogoText2()

            Spacer(Modifier.height(60.dp))

            SignInScreenKakaoLoginButton {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    // 카카오톡 실행이 가능할 때
                    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                        if (error != null) {
                            LogUtil.e("KakaoLoginError", "${error}")
                        } else if (token != null) {
                            LogUtil.e("KakaoLoginSuccess", "accessToken: ${token.accessToken}\n" +
                                    "refreshToken: ${token.refreshToken}")
                            UserApiClient.instance.me { user, error ->
                                if (error != null) {
                                    LogUtil.e("KakaoGetUserInfoError", "$error")
                                }
                                else if (user != null) {
                                    LogUtil.e("KakaoGetUserInfoSuccess", "회원번호: ${user.id}" +
                                            "\n이메일: ${user.kakaoAccount?.email}" +
                                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            SignInScreenGoogleLoginButton {
                getGoogleSignInClient(context).signOut()
                LogUtil.e("signed in email", "${GoogleSignIn.getLastSignedInAccount(context)?.email}")
                startForResult.launch(1)
            }

            Spacer(Modifier.height(24.dp))

            SignInScreenGuestModeText {

            }
        }
    }
}

@ScreenPreview
@Composable
private fun SignInScreenPreview() {

}
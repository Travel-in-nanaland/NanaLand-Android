package com.jeju.nanaland.ui.signin

import android.annotation.SuppressLint
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signin.SignInScreenGoogleLoginButton
import com.jeju.nanaland.ui.component.signin.SignInScreenGuestModeText
import com.jeju.nanaland.ui.component.signin.SignInScreenKakaoLoginButton
import com.jeju.nanaland.ui.component.signin.SignInScreenLogoImage
import com.jeju.nanaland.ui.component.signin.SignInScreenLogoText1
import com.jeju.nanaland.ui.component.signin.SignInScreenLogoText2
import com.jeju.nanaland.util.intent.DeepLinkData
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.signin.AuthResultContract
import com.jeju.nanaland.util.signin.getGoogleSignInClient
import com.jeju.nanaland.util.ui.ScreenPreview
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient


@Composable
fun SignInScreen(
    deepLinkData: DeepLinkData,
    moveToMainScreen: () -> Unit,
    moveToSignUpScreen: (String, String, String) -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        if (deepLinkData.contentId != null) {
            viewModel.nonMemberSignUp(
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID),
                moveToMainScreen
            )
        }
    }
    SignInScreen(
        signIn = viewModel::signIn,
        nonMemberSignUp = viewModel::nonMemberSignUp,
        moveToMainScreen = moveToMainScreen,
        moveToSignUpScreen = moveToSignUpScreen,
        isContent = true
    )
}

@SuppressLint("HardwareIds")
@Composable
private fun SignInScreen(
    signIn: (String, String, () -> Unit, () -> Unit) -> Unit,
    nonMemberSignUp: (String, () -> Unit) -> Unit,
    moveToMainScreen: () -> Unit,
    moveToSignUpScreen: (String, String, String) -> Unit,
    isContent: Boolean
) {
    LaunchedEffect(Unit) {
        UserApiClient.instance.me { user, error ->
            if (user != null) {
                LogUtil.e(
                    "KakaoGetUserInfoSuccess", "회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
            } else {
                LogUtil.e("", "로그인 안됨")
            }
        }
    }
    val context = LocalContext.current
    val startForResult =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task: Task<GoogleSignInAccount>? ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    LogUtil.e("error", "${task?.exception}")
                } else {
                    LogUtil.e("success", "email: ${account.email}\n" +
                            "id: ${account.id}\n" +
                            "birthdate: ${account}"
                    )
                    if (account.id != null) {
                        signIn(
                            "GOOGLE",
                            account.id!!,
                            moveToMainScreen,
                            { moveToSignUpScreen("GOOGLE", account.email!!, account.id!!) }
                        )
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
            Spacer(Modifier.height(150.dp))

            SignInScreenLogoImage()

            Spacer(Modifier.height(16.dp))

            SignInScreenLogoText1()

            SignInScreenLogoText2()

            Spacer(Modifier.weight(1f))

            if (getLanguage() == LanguageType.Korean) {
                SignInScreenKakaoLoginButton {
                    LogUtil.e("keyHash", "${KakaoSdk.keyHash}")
                    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                        // 카카오톡 실행이 가능할 때
                        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                            if (error != null) {
                                LogUtil.e("KakaoLoginError", "${error}")
                                // 카톡은 깔려있는데 에러가 날 경우
                                UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                                    if (error != null) {
                                        Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                                    } else if (token != null) {
                                        UserApiClient.instance.me { user, error ->
                                            if (error != null) {
                                                LogUtil.e("KakaoGetUserInfoError", "$error")
                                            }
                                            else if (user != null) {
                                                LogUtil.e("KakaoGetUserInfoSuccess", "회원번호: ${user.id}" +
                                                        "\n이메일: ${user.kakaoAccount?.email}" +
                                                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                                                if (user.id != null) {
                                                    signIn(
                                                        "KAKAO",
                                                        user.id.toString(),
                                                        moveToMainScreen,
                                                        { moveToSignUpScreen("KAKAO", user.kakaoAccount?.email!!, user.id.toString()) }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
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

                                        if (user.id != null) {
                                            signIn(
                                                "KAKAO",
                                                user.id.toString(),
                                                moveToMainScreen,
                                                { moveToSignUpScreen("KAKAO", user.kakaoAccount?.email!!, user.id.toString()) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                            if (error != null) {
                                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
                            } else if (token != null) {
                                UserApiClient.instance.me { user, error ->
                                    if (error != null) {
                                        LogUtil.e("KakaoGetUserInfoError", "$error")
                                    }
                                    else if (user != null) {
                                        LogUtil.e("KakaoGetUserInfoSuccess", "회원번호: ${user.id}" +
                                                "\n이메일: ${user.kakaoAccount?.email}" +
                                                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                                        if (user.id != null) {
                                            signIn(
                                                "KAKAO",
                                                user.id.toString(),
                                                moveToMainScreen,
                                                { moveToSignUpScreen("KAKAO", user.kakaoAccount?.email!!, user.id.toString()) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            SignInScreenGoogleLoginButton {
                getGoogleSignInClient(context).signOut()
                startForResult.launch(0)
            }

            Spacer(Modifier.height(24.dp))

            SignInScreenGuestModeText {
                nonMemberSignUp(
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID),
                    moveToMainScreen
                )
            }

            Spacer(Modifier.height(40.dp))
        }
    }
}

@ScreenPreview
@Composable
private fun SignInScreenPreview() {

}
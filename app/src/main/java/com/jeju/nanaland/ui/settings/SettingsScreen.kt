package com.jeju.nanaland.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.nonmember.NonMemberGuideDialog
import com.jeju.nanaland.ui.component.settings.SettingsScreenCategoryItem
import com.jeju.nanaland.ui.component.settings.SettingsScreenCategoryTitle
import com.jeju.nanaland.ui.component.settings.SettingsScreenHorizontalDivider
import com.jeju.nanaland.ui.component.settings.SettingsScreenTopBar
import com.jeju.nanaland.ui.component.settings.SettingsScreenVersionText
import com.jeju.nanaland.ui.component.signout.SignOutConfirmDialog
import com.jeju.nanaland.util.resource.getString

@Composable
fun SettingsScreen(
    moveToBackScreen: () -> Unit,
    moveToPolicySettingScreen: () -> Unit,
    moveToPermissionCheckingScreen: () -> Unit,
    moveToLanguageChangeScreen: () -> Unit,
    moveToWithdrawalScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    SettingsScreen(
        signOut = viewModel::signOut,
        moveToBackScreen = moveToBackScreen,
        moveToPolicySettingScreen = moveToPolicySettingScreen,
        moveToPermissionCheckingScreen = moveToPermissionCheckingScreen,
        moveToWithdrawalScreen = moveToWithdrawalScreen,
        moveToLanguageInitScreen = moveToLanguageInitScreen,
        moveToLanguageChangeScreen = moveToLanguageChangeScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun SettingsScreen(
    signOut: (() -> Unit) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToPolicySettingScreen: () -> Unit,
    moveToPermissionCheckingScreen: () -> Unit,
    moveToLanguageChangeScreen: () -> Unit,
    moveToWithdrawalScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val isSignOutDialogShowing = remember { mutableStateOf(false) }
    val isNonMemberGuideDialogShowing = remember { mutableStateOf(false) }

    CustomSurface {
        SettingsScreenTopBar {
            moveToBackScreen()
        }

        Spacer(Modifier.height(20.dp))

        SettingsScreenCategoryTitle(text = getString(R.string.settings_screen_사용_설정))

        Spacer(Modifier.height(6.dp))

        SettingsScreenCategoryItem(
            text = getString(R.string.settings_screen_약관_및_정책),
            onClick = {
                if (UserData.provider == "GUEST") {
                    isNonMemberGuideDialogShowing.value = true
                } else {
                    moveToPolicySettingScreen()
                }
            }
        )

        SettingsScreenCategoryItem(
            text = getString(R.string.settings_screen_접근권한_안내),
            onClick = { moveToPermissionCheckingScreen() }
        )

        SettingsScreenCategoryItem(
            text = getString(R.string.settings_screen_언어_설정),
            onClick = { moveToLanguageChangeScreen() }
        )

        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            SettingsScreenCategoryItem(
                text = getString(R.string.settings_screen_버전_정보),
                onClick = {}
            )

            SettingsScreenVersionText(BuildConfig.VERSION_NAME)
        }

        Spacer(Modifier.height(4.dp))

        SettingsScreenHorizontalDivider()

        Spacer(Modifier.height(4.dp))

        SettingsScreenCategoryItem(
            text = getString(R.string.settings_screen_로그아웃),
            onClick = {
                if (UserData.provider == "GUEST") {
                    isNonMemberGuideDialogShowing.value = true
                } else {
                    isSignOutDialogShowing.value = true
                }
            }
        )

        SettingsScreenCategoryItem(
            text = getString(R.string.settings_screen_회원_탈퇴),
            onClick = { moveToWithdrawalScreen() }
        )
    }

    if (isNonMemberGuideDialogShowing.value) {
        NonMemberGuideDialog(
            onCloseClick = { isNonMemberGuideDialogShowing.value = false },
            moveToSignInScreen = moveToSignInScreen
        )
    }

    if (isSignOutDialogShowing.value) {
        SignOutConfirmDialog(
            onConfirm = { signOut(moveToLanguageInitScreen) },
            onCancel = { isSignOutDialogShowing.value = false }
        )
    }
}
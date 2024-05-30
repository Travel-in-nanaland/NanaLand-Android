package com.jeju.nanaland.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.settings.SettingsScreenCategoryItem
import com.jeju.nanaland.ui.component.settings.SettingsScreenCategoryTitle
import com.jeju.nanaland.ui.component.settings.SettingsScreenHorizontalDivider
import com.jeju.nanaland.ui.component.settings.SettingsScreenTopBar
import com.jeju.nanaland.ui.component.settings.SettingsScreenVersionText

@Composable
fun SettingsScreen(
    moveToBackScreen: () -> Unit,
    moveToPolicySettingScreen: () -> Unit,
    moveToPermissionCheckingScreen: () -> Unit,
    moveToWithdrawalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    SettingsScreen(
        signOut = viewModel::signOut,
        moveToBackScreen = moveToBackScreen,
        moveToPolicySettingScreen = moveToPolicySettingScreen,
        moveToPermissionCheckingScreen = moveToPermissionCheckingScreen,
        moveToWithdrawalScreen = moveToWithdrawalScreen,
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
    moveToWithdrawalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        SettingsScreenTopBar {
            moveToBackScreen()
        }

        Spacer(Modifier.height(20.dp))

        SettingsScreenCategoryTitle(text = "사용 설정")

        Spacer(Modifier.height(6.dp))

        SettingsScreenCategoryItem(
            text = "약관 및 정책",
            onClick = { moveToPolicySettingScreen() }
        )

        SettingsScreenCategoryItem(
            text = "접근권한 안내",
            onClick = { moveToPermissionCheckingScreen() }
        )

        SettingsScreenCategoryItem(
            text = "언어 설정",
            onClick = {}
        )

        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            SettingsScreenCategoryItem(
                text = "버전 정보",
                onClick = {}
            )

            SettingsScreenVersionText(BuildConfig.VERSION_NAME)
        }

        Spacer(Modifier.height(4.dp))

        SettingsScreenHorizontalDivider()

        Spacer(Modifier.height(4.dp))

        SettingsScreenCategoryItem(
            text = "로그아웃",
            onClick = { signOut(moveToSignInScreen) }
        )

        SettingsScreenCategoryItem(
            text = "회원 탈퇴",
            onClick = { moveToWithdrawalScreen() }
        )
    }
}
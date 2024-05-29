package com.jeju.nanaland.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
) {
    SettingsScreen(
        moveToBackScreen = moveToBackScreen,
        moveToPolicySettingScreen = moveToPolicySettingScreen,
        isContent = true
    )
}

@Composable
private fun SettingsScreen(
    moveToBackScreen: () -> Unit,
    moveToPolicySettingScreen: () -> Unit,
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
            onClick = {}
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

        SettingsScreenHorizontalDivider()

        SettingsScreenCategoryItem(
            text = "로그아웃",
            onClick = {}
        )

        SettingsScreenCategoryItem(
            text = "회원 탈퇴",
            onClick = {}
        )
    }
}
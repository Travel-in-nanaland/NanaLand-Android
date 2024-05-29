package com.jeju.nanaland.ui.component.settings

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.CustomTopBar

@Composable
fun SettingsScreenTopBar(onClick: () -> Unit) {
    CustomTopBar(
        title = "설정",
        onBackButtonClicked = onClick
    )
}
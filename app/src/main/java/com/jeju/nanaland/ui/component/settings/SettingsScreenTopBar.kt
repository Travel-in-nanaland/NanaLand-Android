package com.jeju.nanaland.ui.component.settings

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.util.resource.getString

@Composable
fun SettingsScreenTopBar(onClick: () -> Unit) {
    CustomTopBar(
        title = getString(R.string.settings_screen_설정),
        onBackButtonClicked = onClick
    )
}
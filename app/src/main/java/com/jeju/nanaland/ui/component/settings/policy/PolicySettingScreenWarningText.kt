package com.jeju.nanaland.ui.component.settings.policy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun PolicySettingScreenWarningText() {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        text = getString(R.string.policy_setting_screen_warning),
        color = getColor().gray01,
        style = caption01
    )
}
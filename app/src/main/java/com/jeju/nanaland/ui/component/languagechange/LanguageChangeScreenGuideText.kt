package com.jeju.nanaland.ui.component.languagechange

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString

@Composable
fun LanguageChangeScreenGuideText() {
    Text(
        text = getString(R.string.language_change_screen_guide),
        color = getColor().black,
        style = title02
    )
}
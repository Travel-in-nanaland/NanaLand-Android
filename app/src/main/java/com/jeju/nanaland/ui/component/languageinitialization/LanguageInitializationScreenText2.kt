package com.jeju.nanaland.ui.component.languageinitialization

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.resource.getString

@Composable
fun LanguageInitializationScreenText2() {
    Text(
        text = getString(R.string.profile_initialization_screen_sub_title),
        color = getColor().white,
        style = largeTitle01
    )
}
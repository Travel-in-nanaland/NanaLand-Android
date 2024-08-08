package com.jeju.nanaland.ui.component.languageinitialization

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.appleSdGothicNeo
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02Regular
import com.jeju.nanaland.util.resource.getString

@Composable
fun LanguageInitializationScreenText1() {
    Text(
        text = getString(R.string.profile_initialization_screen_hello),
        color = getColor().white,
        style = largeTitle02Regular
    )
}
package com.jeju.nanaland.ui.component.signup.typetestloading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestLoadingScreenText1() {
    Text(
        text = getString(R.string.type_test_screen_text3),
        color = getColor().main,
        style = largeTitle02,
        textAlign = TextAlign.Center
    )
}
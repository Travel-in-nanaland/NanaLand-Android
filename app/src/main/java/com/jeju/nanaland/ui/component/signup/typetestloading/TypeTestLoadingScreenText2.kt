package com.jeju.nanaland.ui.component.signup.typetestloading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestLoadingScreenText2() {
    Text(
        text = getString(R.string.type_test_screen_text4),
        color = getColor().black,
        style = title02,
        textAlign = TextAlign.Center
    )
}
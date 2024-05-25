package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.bodySemiBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ColumnScope.TypeTestingScreenLevelText(level: Int) {
    Text(
        modifier = Modifier.align(Alignment.End),
        text = "${level}/5",
        textAlign = TextAlign.Center,
        color = getColor().main,
        style = bodySemiBold
    )
}
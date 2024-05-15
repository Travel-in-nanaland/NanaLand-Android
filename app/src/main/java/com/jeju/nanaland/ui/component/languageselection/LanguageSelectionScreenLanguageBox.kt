package com.jeju.nanaland.ui.component.languageselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun LanguageSelectionScreenLanguageBox(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(48.dp)
            .background(
                color = getColor().main,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = getColor().white,
            style = body01
        )
    }
}
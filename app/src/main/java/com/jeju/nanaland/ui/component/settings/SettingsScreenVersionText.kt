package com.jeju.nanaland.ui.component.settings

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun BoxScope.SettingsScreenVersionText(text: String) {
    Text(
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(end = 16.dp),
        text = text,
        color = getColor().black,
        style = body01
    )
}
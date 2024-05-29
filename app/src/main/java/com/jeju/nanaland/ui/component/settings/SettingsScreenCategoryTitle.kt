package com.jeju.nanaland.ui.component.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SettingsScreenCategoryTitle(text: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = text,
        color = getColor().black,
        style = body02Bold
    )
}
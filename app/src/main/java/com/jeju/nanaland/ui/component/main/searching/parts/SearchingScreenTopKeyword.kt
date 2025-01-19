package com.jeju.nanaland.ui.component.main.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchingScreenTopKeyword(
    text: String?,
    rank: Int,
    onClick: () -> Unit,
) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = text ?: "",
        color = if (rank < 3) getColor().main else getColor().black,
        style = body02
    )
}
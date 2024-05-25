package com.jeju.nanaland.ui.component.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun BottomOkButtonOutlined(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(48.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = getColor().main,
                ),
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = getColor().main,
            style = bodyBold
        )
    }
}
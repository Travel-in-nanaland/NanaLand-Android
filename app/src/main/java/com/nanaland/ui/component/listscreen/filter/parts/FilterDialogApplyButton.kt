package com.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.bodyBold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun FilterDialogApplyButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(
                color = getColor().main,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "적용하기",
            color = getColor().white,
            style = bodyBold
        )
    }
}
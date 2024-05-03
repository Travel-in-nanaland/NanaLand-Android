package com.nanaland.ui.component.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.drawColoredShadow

@Composable
fun CustomTopBarWithShadow(
    title: String,
    onBackButtonClicked: () -> Unit,
) {
    CustomTopBar(
        modifier = Modifier
            .zIndex(10f)
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.1f,
                shadowRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            ),
        title = title,
        onBackButtonClicked = onBackButtonClicked
    )
}
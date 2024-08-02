package com.jeju.nanaland.ui.component.listscreen.category.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun RowScope.CategoryBox(
    text: String,
    isSelected: Boolean,
    updateSelectedCategoryType: () -> Unit,
) {
    val mainColor = getColor().main
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickableNoEffect {
                updateSelectedCategoryType()
            }
            .drawBehind {
                if (isSelected) {
                    val borderSize = 2.dp.toPx()
                    drawLine(
                        color = mainColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF000000),
            style = if (isSelected) body02SemiBold else body02,
            textAlign = TextAlign.Center
        )
    }
}
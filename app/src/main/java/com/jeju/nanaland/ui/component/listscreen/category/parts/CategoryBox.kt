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
    categoryIdx: Int,
    selectedCategoryType: FestivalCategoryType,
    updateSelectedCategoryType: (FestivalCategoryType) -> Unit,
) {
    val mainColor = getColor().main
    val titleList = remember {
        listOf(FestivalCategoryType.Monthly, FestivalCategoryType.Ended, FestivalCategoryType.Seasonal)
    }
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickableNoEffect {
                if (selectedCategoryType != titleList[categoryIdx]) {
                    updateSelectedCategoryType(titleList[categoryIdx])
                }
            }
            .drawBehind {
                if (titleList[categoryIdx] == selectedCategoryType) {
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
            text = when (categoryIdx) {
                0 -> getString(R.string.festival_list_screen_월별_축제)
                1 -> getString(R.string.festival_list_screen_종료된_축제)
                else -> getString(R.string.festival_list_screen_계절별_축제)
            },
            color = Color(0xFF000000),
            style = if (selectedCategoryType == titleList[categoryIdx]) body02SemiBold else body02
        )
    }
}
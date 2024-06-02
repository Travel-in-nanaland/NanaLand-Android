package com.jeju.nanaland.ui.component.main.searchresult

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchResultScreenCategorySelectionTab(
    getSearchResult: () -> Unit,
    selectedCategory: SearchCategoryType,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
) {
    val mainColor = getColor().main
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        SearchCategoryType.entries.toTypedArray().forEachIndexed { idx, item ->
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .drawBehind {
                        if (selectedCategory == item) {
                            val borderSize = 2.dp.toPx()
                            drawLine(
                                color = mainColor,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = borderSize
                            )
                        }
                    }
                    .clickableNoEffect {
                        if (selectedCategory != item) {
                            updateSelectedCategory(item)
                            getSearchResult()
                        }
                    }
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getString(item.titleResId),
                    color = getColor().black,
                    style = if (selectedCategory == item) body02SemiBold else body02
                )
            }
        }
    }
}
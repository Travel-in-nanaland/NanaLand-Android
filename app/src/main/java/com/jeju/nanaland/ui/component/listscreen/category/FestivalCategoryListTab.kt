package com.jeju.nanaland.ui.component.listscreen.category

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.ui.component.listscreen.category.parts.CategoryBox
import com.jeju.nanaland.util.resource.getString

@Composable
fun FestivalCategoryListTab(
    selectedCategoryType: FestivalCategoryType,
    updateSelectedCategoryType: (FestivalCategoryType) -> Unit,
) {
    val titleList = remember { FestivalCategoryType.entries }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        FestivalCategoryType.entries.forEach {
            CategoryBox(
                text = when (it) {
                    FestivalCategoryType.Monthly -> getString(R.string.festival_list_screen_월별_축제)
                    FestivalCategoryType.Ended -> getString(R.string.festival_list_screen_종료된_축제)
                    else -> getString(R.string.festival_list_screen_계절별_축제)
                },
                isSelected = titleList[it.ordinal] == selectedCategoryType,
                updateSelectedCategoryType = {
                    updateSelectedCategoryType(titleList[it.ordinal])
                }
            )
        }
    }
}
package com.jeju.nanaland.ui.component.listscreen.category

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.ui.component.listscreen.category.parts.CategoryBox
import com.jeju.nanaland.util.resource.getString

@Composable
fun ExperienceCategoryListTab(
    selectedCategoryType: ExperienceCategoryType,
    updateSelectedCategoryType: (ExperienceCategoryType) -> Unit,
) {
    val titleList = remember {
        listOf(ExperienceCategoryType.Activity, ExperienceCategoryType.CultureArt)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        repeat(2) {
            CategoryBox(
                text = when (it) {
                    0 -> "액티비티"
                    else -> "문화예술"
                },
                isSelected = titleList[it] == selectedCategoryType,
                updateSelectedCategoryType = {
                    updateSelectedCategoryType(titleList[it])
                }
            )
        }
    }
}
package com.nanaland.ui.component.listscreen.category

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.globalvalue.type.FestivalCategoryType
import com.nanaland.ui.component.listscreen.category.parts.CategoryBox

@Composable
fun CategoryListTab(
    selectedCategoryType: FestivalCategoryType,
    updateSelectedCategoryType: (FestivalCategoryType) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    ) {
        repeat(3) {
            CategoryBox(
                categoryIdx = it,
                selectedCategoryType = selectedCategoryType,
                updateSelectedCategoryType = updateSelectedCategoryType
            )
        }
    }
}
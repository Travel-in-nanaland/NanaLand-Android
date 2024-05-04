package com.nanaland.ui.component.main.searchresult.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun SearchResultScreenPreviewByCategoryHorizontalDivider() {
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(getColor().gray02)
    )
}

@ComponentPreview
@Composable
private fun SearchResultScreenPreviewByCategoryHorizontalDividerPreview() {
    NanaLandTheme {
        SearchResultScreenPreviewByCategoryHorizontalDivider()
    }
}
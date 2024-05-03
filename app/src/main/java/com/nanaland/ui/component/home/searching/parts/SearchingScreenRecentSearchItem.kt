package com.nanaland.ui.component.home.searching.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenRecentSearchItem(
    text: String,
    onCloseClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(34.dp)
            .background(
                color = getColor().main10,
                shape = RoundedCornerShape(50)
            )
            .padding(start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchingScreenRecentSearchItemText(text = "Text")

            Spacer(Modifier.width(10.dp))

            SearchingScreenRecentSearchItemCloseIcon { onCloseClick() }
        }
    }
}

@ComponentPreview
@Composable
private fun SearchingScreenRecentSearchItemPreview() {
    NanaLandTheme {
        SearchingScreenRecentSearchItem(
            text = "Text",
            onCloseClick = {}
        )
    }
}
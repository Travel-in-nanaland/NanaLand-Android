package com.nanaland.ui.component.home.searching.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchingScreenRecentSearchItemCloseIcon(onClick: () -> Unit) {
    Image(
        modifier = Modifier
            .size(16.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(id = R.drawable.ic_close),
        contentDescription = null,
        colorFilter = ColorFilter.tint(getColor().main)
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenRecentSearchItemCloseIconPreview() {
    NanaLandTheme {
        SearchingScreenRecentSearchItemCloseIcon {}
    }
}
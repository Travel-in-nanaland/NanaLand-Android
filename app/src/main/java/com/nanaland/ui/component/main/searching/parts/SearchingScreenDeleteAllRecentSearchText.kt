package com.nanaland.ui.component.main.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.caption01
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchingScreenDeleteAllRecentSearchText(onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = "모두 지우기",
        color = getColor().gray01,
        style = caption01
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenDeleteAllRecentSearchTextPreview() {
    NanaLandTheme {
        SearchingScreenDeleteAllRecentSearchText {}
    }
}
package com.jeju.nanaland.ui.component.main.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchingScreenDeleteAllRecentSearchText(onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = getString(R.string.searching_screen_모두_지우기),
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
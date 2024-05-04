package com.nanaland.ui.component.main.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.body02SemiBold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchingScreenTopKeyword(
    text: String?,
    rank: Int,
    onClick: () -> Unit,
) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = "${rank + 1}. ${text ?: ""}" ,
        color = if (rank == 0 || rank == 1) getColor().main else getColor().gray01,
        style = if (rank == 0 || rank == 1) body02SemiBold else body02
    )
}
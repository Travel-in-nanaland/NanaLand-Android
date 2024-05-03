package com.nanaland.ui.component.home.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.body02SemiBold
import com.nanaland.ui.theme.getColor

@Composable
fun SearchingScreenTopKeyword(
    text: String?,
    rank: Int
) {
    Text(
        text = "${rank + 1}. ${text ?: ""}" ,
        color = if (rank == 0 || rank == 1) getColor().main else getColor().gray01,
        style = if (rank == 0 || rank == 1) body02SemiBold else body02
    )
}
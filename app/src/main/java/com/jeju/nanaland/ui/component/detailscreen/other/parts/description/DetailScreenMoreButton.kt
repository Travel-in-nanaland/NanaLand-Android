package com.jeju.nanaland.ui.component.detailscreen.other.parts.description

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
fun DetailScreenMoreButton(
    isMoreOpen: Boolean,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = if (isMoreOpen) getString(R.string.detail_screen_common_접기) else getString(R.string.detail_screen_common_더보기),
        color = getColor().gray01,
        style = caption01
    )
}

@ComponentPreview
@Composable
private fun DetailScreenMoreButtonPreview() {
    NanaLandTheme {
        DetailScreenMoreButton(
            isMoreOpen = false,
            onClick = {}
        )
    }
}
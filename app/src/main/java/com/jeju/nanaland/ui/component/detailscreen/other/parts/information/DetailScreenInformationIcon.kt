package com.jeju.nanaland.ui.component.detailscreen.other.parts.information

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenInformationIcon(@DrawableRes id: Int) {
    Image(
        modifier = Modifier.size(24.dp),
        painter = painterResource(id),
        contentDescription = null,
        colorFilter = ColorFilter.tint(getColor().main)
    )
}

@ComponentPreview
@Composable
private fun DetailScreenInformationIconPreview() {
    NanaLandTheme {
        DetailScreenInformationIcon(R.drawable.ic_heart_outlined)
    }
}
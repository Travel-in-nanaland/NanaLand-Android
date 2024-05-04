package com.nanaland.ui.component.detailscreen.other.parts.information

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenInformationIcon(@DrawableRes id: Int) {
    Image(
        modifier = Modifier.size(32.dp),
        painter = painterResource(id),
        contentDescription = null
    )
}

@ComponentPreview
@Composable
private fun DetailScreenInformationIconPreview() {
    NanaLandTheme {
        DetailScreenInformationIcon(R.drawable.ic_heart_outlined)
    }
}
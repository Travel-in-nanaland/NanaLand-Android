package com.nanaland.ui.component.detailscreen.parts.description

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun DetailScreenShareButton(onClick: () -> Unit) {
    Image(
        modifier = Modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(R.drawable.ic_share_outlined_black),
        contentDescription = null
    )
}

@ComponentPreview
@Composable
private fun DetailScreenShareButtonPreview() {
    NanaLandTheme {
        DetailScreenShareButton {}
    }
}
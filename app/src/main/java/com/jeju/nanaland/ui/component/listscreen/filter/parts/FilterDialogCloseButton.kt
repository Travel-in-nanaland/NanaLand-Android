package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun FilterDialogCloseButton(
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(R.drawable.ic_close),
        contentDescription = null
    )
}
package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun TypeTestingScreenBackButton(onClick: () -> Unit,) {
    Image(
        modifier = Modifier
            .padding(start = 16.dp)
            .size(48.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(R.drawable.ic_arrow_left_circled),
        contentDescription = null,
        colorFilter = ColorFilter.tint(getColor().main)
    )
}
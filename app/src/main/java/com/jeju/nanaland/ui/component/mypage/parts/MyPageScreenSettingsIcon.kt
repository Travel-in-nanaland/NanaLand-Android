package com.jeju.nanaland.ui.component.mypage.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun BoxScope.MyPageScreenSettingsIcon(onClick: () -> Unit) {
    Image(
        modifier = Modifier
            .padding(end = 16.dp)
            .align(Alignment.CenterEnd)
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(R.drawable.ic_gear_outlined),
        contentDescription = null
    )
}
package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun JejuMapImage() {
    Image(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        painter = painterResource(R.drawable.jeju_map),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}
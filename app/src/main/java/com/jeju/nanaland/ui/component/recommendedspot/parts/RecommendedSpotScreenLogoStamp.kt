package com.jeju.nanaland.ui.component.recommendedspot.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun RecommendedSpotScreenLogoStamp() {
    Image(
        modifier = Modifier.size(60.dp),
        painter = painterResource(R.drawable.img_logo_stamp),
        contentDescription = null
    )
}
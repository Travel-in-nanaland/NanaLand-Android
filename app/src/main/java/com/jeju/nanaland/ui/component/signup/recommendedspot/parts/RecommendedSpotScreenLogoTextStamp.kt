package com.jeju.nanaland.ui.component.signup.recommendedspot.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun RecommendedSpotScreenLogoTextStamp() {
    Image(
        modifier = Modifier.width(100.dp),
        painter = painterResource(R.drawable.img_logo_stamp_text),
        contentDescription = null
    )
}
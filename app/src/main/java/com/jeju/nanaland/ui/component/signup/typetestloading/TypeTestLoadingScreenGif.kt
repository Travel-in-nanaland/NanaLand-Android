package com.jeju.nanaland.ui.component.signup.typetestloading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R
import kotlin.random.Random

@Composable
fun TypeTestLoadingScreenGif() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(when (Random.nextInt(0, 2)) {
            0 -> R.raw.loading_orange
            else -> R.raw.loading_beverage
        })
    )
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        composition = composition,
        progress = { progress },
    )
}
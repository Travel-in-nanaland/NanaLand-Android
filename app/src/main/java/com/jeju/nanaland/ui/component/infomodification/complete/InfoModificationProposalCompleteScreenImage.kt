package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R

@Composable
fun InfoModificationProposalCompleteScreenImage() {
    val rawRes = remember {
        listOf(
            R.raw.info_modify_complete_balloon,
            R.raw.info_modify_complete_speaker
        ).random()
    }
    val gif by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))
    LottieAnimation(
        modifier = Modifier.size(250.dp, 230.dp),
        composition = gif,
        iterations = LottieConstants.IterateForever
    )
}
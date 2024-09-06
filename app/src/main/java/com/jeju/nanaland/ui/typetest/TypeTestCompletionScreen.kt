package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestcompletion.TypeTestCompletionScreenBottomButton
import com.jeju.nanaland.ui.component.signup.typetestcompletion.TypeTestCompletionScreenText

@Composable
fun TypeTestCompletionScreen(
    moveToTypeTestLoadingScreen: () -> Unit,
) {
    TypeTestCompletionScreen(
        moveToTypeTestLoadingScreen = moveToTypeTestLoadingScreen,
        isContent = true
    )
}

@Composable
private fun TypeTestCompletionScreen(
    moveToTypeTestLoadingScreen: () -> Unit,
    isContent: Boolean
) {
    val gif by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_for_test_type))

    CustomSurface {
        Spacer(Modifier.height(110.dp))

        TypeTestCompletionScreenText()

        LottieAnimation(
            modifier = Modifier
                .heightIn(max = 175.dp)
                .align(Alignment.CenterHorizontally),
            composition = gif,
            iterations = LottieConstants.IterateForever
        )

        Spacer(Modifier.weight(1f))

        TypeTestCompletionScreenBottomButton {
            moveToTypeTestLoadingScreen()
        }

        Spacer(Modifier.height(20.dp))
    }
}
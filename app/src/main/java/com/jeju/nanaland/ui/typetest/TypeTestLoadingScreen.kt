package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestloading.TypeTestLoadingScreenGif
import com.jeju.nanaland.ui.component.signup.typetestloading.TypeTestLoadingScreenText1
import com.jeju.nanaland.ui.component.signup.typetestloading.TypeTestLoadingScreenText2
import kotlinx.coroutines.delay

@Composable
fun TypeTestLoadingScreen(
    moveToTypeTestResultScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000)
        moveToTypeTestResultScreen()
    }
    TypeTestLoadingScreen(
        isContent = true
    )
}

@Composable
private fun TypeTestLoadingScreen(
    isContent: Boolean
) {
    CustomSurface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TypeTestLoadingScreenGif()

            TypeTestLoadingScreenText1()

            Spacer(Modifier.height(4.dp))

            TypeTestLoadingScreenText2()
        }
    }
}
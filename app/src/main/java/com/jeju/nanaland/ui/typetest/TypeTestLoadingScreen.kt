package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestloading.TypeTestLoadingScreenGif
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString
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

            Text(
                text = getString(R.string.type_test_screen_text_page2_text1),
                color = getColor().main,
                style = largeTitle02,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = getString(R.string.type_test_screen_text_page2_text2),
                color = getColor().black,
                style = title02,
                textAlign = TextAlign.Center
            )
        }
    }
}
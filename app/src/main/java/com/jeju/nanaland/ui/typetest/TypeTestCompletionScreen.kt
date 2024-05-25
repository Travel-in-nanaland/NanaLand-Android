package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestcompletion.TypeTestCompletionScreenBottomButton
import com.jeju.nanaland.ui.component.signup.typetestcompletion.TypeTestCompletionScreenText
import com.jeju.nanaland.ui.theme.getColor

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
    CustomSurface {
        Spacer(Modifier.height(110.dp))

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TypeTestCompletionScreenText()

            Spacer(Modifier.height(50.dp))

            Spacer(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = getColor().main,
                        shape = RoundedCornerShape(50)
                    )
            )

            Spacer(Modifier.height(40.dp))

            Spacer(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = getColor().main50,
                        shape = RoundedCornerShape(50)
                    )
            )

            Spacer(Modifier.height(40.dp))

            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = getColor().main10,
                        shape = RoundedCornerShape(50)
                    )
            )

            Spacer(Modifier.height(40.dp))

            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = getColor().main10,
                        shape = RoundedCornerShape(50)
                    )
            )
        }

        Spacer(Modifier.weight(1f))

        TypeTestCompletionScreenBottomButton {
            moveToTypeTestLoadingScreen()
        }

        Spacer(Modifier.height(20.dp))
    }
}
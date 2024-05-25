package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenBottomButton1
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenBottomButton2
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenImage
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText1
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText2
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText3

@Composable
fun TypeTestResultScreen(
    moveToMainScreen: () -> Unit,
) {
    TypeTestResultScreen(
        moveToMainScreen = moveToMainScreen,
        isContent = true
    )
}

@Composable
private fun TypeTestResultScreen(
    moveToMainScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Spacer(Modifier.height(60.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TypeTestResultScreenText1()

            Spacer(Modifier.height(4.dp))

            TypeTestResultScreenText2()

            Spacer(Modifier.height(75.dp))

            TypeTestResultScreenImage()

            Spacer(Modifier.height(32.dp))

            TypeTestResultScreenText3()

            Spacer(Modifier.weight(1f))

            TypeTestResultScreenBottomButton1 {

            }

            Spacer(Modifier.height(16.dp))

            TypeTestResultScreenBottomButton2 { moveToMainScreen() }

            Spacer(Modifier.height(20.dp))
        }
    }
}
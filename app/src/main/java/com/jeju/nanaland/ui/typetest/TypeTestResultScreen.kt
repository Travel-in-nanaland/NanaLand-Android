package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenBottomButton1
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenBottomButton2
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenImage
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText1
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText2
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText3
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun TypeTestResultScreen(
    travelType: String,
    moveToRecommendedSpotScreen: () -> Unit,
    moveToMainScreen: () -> Unit,
) {
    TypeTestResultScreen(
        travelType = travelType,
        moveToRecommendedSpotScreen = moveToRecommendedSpotScreen,
        moveToMainScreen = moveToMainScreen,
        isContent = true
    )
}

@Composable
private fun TypeTestResultScreen(
    travelType: String,
    moveToRecommendedSpotScreen: () -> Unit,
    moveToMainScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = remember { scrollableVerticalArrangement }
        ) {
            item {
                Box {
                    Box(
                        modifier = Modifier
                            .padding(start = 40.dp, top = 40.dp)
                            .size(85.dp)
                            .clip(CircleShape)
                            .background(getColor().main10)
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(60.dp))

                        TypeTestResultScreenText1()

                        Spacer(Modifier.height(4.dp))

                        TypeTestResultScreenText2(travelType = travelType)

                        Spacer(Modifier.height(32.dp))

                        TypeTestResultScreenImage(travelType = travelType)

                        Spacer(Modifier.height(32.dp))

                        TypeTestResultScreenText3(travelType = travelType)

                        Spacer(Modifier.height(40.dp))
                    }
                }
            }

            item {

                TypeTestResultScreenBottomButton1(travelType = travelType) { moveToRecommendedSpotScreen() }

                Spacer(Modifier.height(16.dp))

                TypeTestResultScreenBottomButton2 { moveToMainScreen() }

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
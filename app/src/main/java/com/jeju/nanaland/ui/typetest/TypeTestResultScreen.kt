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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.globalvalue.constant.toViewString
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenImage
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText1
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText2
import com.jeju.nanaland.ui.component.signup.typetestresult.TypeTestResultScreenText3
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement


@Composable
fun TypeTestResultScreen(
    travelType: TravelType,
    filledButtonString: String,
    moveToRecommendedSpotScreen: () -> Unit,
    onFilledButtonClick: () -> Unit,
    moveToBackScreen: (() -> Unit)?,
) {
    CustomSurface {
        Box {
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

                    BottomOkButtonOutlined(
                        text = travelType.toViewString() + getString(R.string.type_test_screen_button1),
                        onClick = moveToRecommendedSpotScreen
                    )

                    Spacer(Modifier.height(16.dp))

                    BottomOkButton(
                        text = filledButtonString,
                        isActivated = true,
                        onClick = onFilledButtonClick
                    )

                    Spacer(Modifier.height(20.dp))
                }
            }
            moveToBackScreen?.let {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                        .clickableNoEffect { it() },
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = getColor().black
                )
            }
        }

    }
}
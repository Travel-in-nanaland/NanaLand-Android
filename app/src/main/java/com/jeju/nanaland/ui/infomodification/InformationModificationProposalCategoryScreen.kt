package com.jeju.nanaland.ui.infomodification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.infomodification.category.InfoModificationProposalScreenBox
import com.jeju.nanaland.ui.component.infomodification.category.InfoModificationProposalScreenText1
import com.jeju.nanaland.ui.component.infomodification.category.InfoModificationProposalScreenText2
import com.jeju.nanaland.ui.component.infomodification.category.InfoModificationProposalScreenText3

@Composable
fun InformationModificationProposalCategoryScreen(
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalWritingScreen: (String) -> Unit
) {
    InformationModificationProposalCategoryScreen(
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalWritingScreen = moveToInfoModificationProposalWritingScreen,
        isContent = true
    )
}

@Composable
private fun InformationModificationProposalCategoryScreen(
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalWritingScreen: (String) -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        CustomTopBar(
            title = "정보 수정 제안",
            onBackButtonClicked = moveToBackScreen
        )

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
        ) {
            InfoModificationProposalScreenText1()

            Spacer(Modifier.height(4.dp))

            InfoModificationProposalScreenText2()

            Spacer(Modifier.height(32.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_phone_outlined,
                text = "전화번호 및 홈페이지",
                onClick = { moveToInfoModificationProposalWritingScreen("CONTACT_OR_HOMEPAGE") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_clock_outlined,
                text = "운영 시간",
                onClick = { moveToInfoModificationProposalWritingScreen("TIME") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_location_outlined,
                text = "장소명 및 위치",
                onClick = { moveToInfoModificationProposalWritingScreen("LOCATION") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_coin,
                text = "가격 정보",
                onClick = { moveToInfoModificationProposalWritingScreen("PRICE") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_trashcan_outlined,
                text = "장소 삭제",
                onClick = { moveToInfoModificationProposalWritingScreen("DELETE_LOCATION") }
            )

            Spacer(Modifier.height(48.dp))

            InfoModificationProposalScreenText3()

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_present_outlined,
                text = "제공 서비스",
                onClick = { moveToInfoModificationProposalWritingScreen("ETC") }
            )
        }
    }
}
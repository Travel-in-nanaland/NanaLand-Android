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
import com.jeju.nanaland.ui.component.infomodification.InfoModificationProposalScreenBox
import com.jeju.nanaland.ui.component.infomodification.InfoModificationProposalScreenText1
import com.jeju.nanaland.ui.component.infomodification.InfoModificationProposalScreenText2
import com.jeju.nanaland.ui.component.infomodification.InfoModificationProposalScreenText3

@Composable
fun InformationModificationProposalScreen(
    moveToBackScreen: () -> Unit,
    viewModel: InfoModificationProposalViewModel = hiltViewModel()
) {
    InformationModificationProposalScreen(
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun InformationModificationProposalScreen(
    moveToBackScreen: () -> Unit,
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

            Spacer(Modifier.height(28.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_phone_outlined,
                text = "전화번호 및 홈페이지",
                onClick = {}
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_clock_outlined,
                text = "운영 시간",
                onClick = {}
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_location_outlined,
                text = "장소명 및 위치",
                onClick = {}
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_coin,
                text = "가격 정보",
                onClick = {}
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_trashcan_outlined,
                text = "장소 삭제",
                onClick = {}
            )

            Spacer(Modifier.height(48.dp))

            InfoModificationProposalScreenText3()

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_present_outlined,
                text = "제공 서비스",
                onClick = {}
            )
        }
    }
}
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
import com.jeju.nanaland.util.resource.getString

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
            title = getString(R.string.info_modification_proposal_정보_수정_제안2),
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
                text = getString(R.string.info_modification_proposal_전화번호_및_홈페이지),
                onClick = { moveToInfoModificationProposalWritingScreen("CONTACT_OR_HOMEPAGE") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_clock_outlined,
                text = getString(R.string.info_modification_proposal_운영_시간),
                onClick = { moveToInfoModificationProposalWritingScreen("TIME") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_location_outlined,
                text = getString(R.string.info_modification_proposal_장소명_및_위치),
                onClick = { moveToInfoModificationProposalWritingScreen("LOCATION") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_coin,
                text = getString(R.string.info_modification_proposal_가격_정보),
                onClick = { moveToInfoModificationProposalWritingScreen("PRICE") }
            )

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_trashcan_outlined,
                text = getString(R.string.info_modification_proposal_장소_삭제),
                onClick = { moveToInfoModificationProposalWritingScreen("DELETE_LOCATION") }
            )

            Spacer(Modifier.height(48.dp))

            InfoModificationProposalScreenText3()

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalScreenBox(
                drawableId = R.drawable.ic_present_outlined,
                text = getString(R.string.info_modification_proposal_제공_서비스),
                onClick = { moveToInfoModificationProposalWritingScreen("ETC") }
            )
        }
    }
}
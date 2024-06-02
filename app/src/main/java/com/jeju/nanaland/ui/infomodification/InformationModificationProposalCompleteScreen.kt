package com.jeju.nanaland.ui.infomodification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.infomodification.complete.InfoModificationProposalCompleteScreenBottomButton1
import com.jeju.nanaland.ui.component.infomodification.complete.InfoModificationProposalCompleteScreenBottomButton2
import com.jeju.nanaland.ui.component.infomodification.complete.InfoModificationProposalCompleteScreenImage
import com.jeju.nanaland.ui.component.infomodification.complete.InfoModificationProposalCompleteScreenText1
import com.jeju.nanaland.ui.component.infomodification.complete.InfoModificationProposalCompleteScreenText2

@Composable
fun InformationModificationProposalCompleteScreen(
    moveToContentScreen: () -> Unit,
    moveToInfoModificationProposalCategoryScreen: () -> Unit,
) {
    InformationModificationProposalCompleteScreen(
        moveToContentScreen = moveToContentScreen,
        moveToInfoModificationProposalCategoryScreen = moveToInfoModificationProposalCategoryScreen,
        isContent = true
    )
}

@Composable
private fun InformationModificationProposalCompleteScreen(
    moveToContentScreen: () -> Unit,
    moveToInfoModificationProposalCategoryScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(80.dp))

            InfoModificationProposalCompleteScreenImage()

            Spacer(Modifier.height(40.dp))

            InfoModificationProposalCompleteScreenText1()

            Spacer(Modifier.height(8.dp))

            InfoModificationProposalCompleteScreenText2()

            Spacer(Modifier.weight(1f))

            InfoModificationProposalCompleteScreenBottomButton1 { moveToContentScreen() }

            Spacer(Modifier.height(16.dp))

            InfoModificationProposalCompleteScreenBottomButton2 { moveToInfoModificationProposalCategoryScreen() }

            Spacer(Modifier.height(20.dp))
        }
    }
}
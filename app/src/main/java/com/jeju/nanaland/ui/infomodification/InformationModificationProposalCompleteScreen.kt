package com.jeju.nanaland.ui.infomodification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun InformationModificationProposalCompleteScreen(
    moveToContentScreen: () -> Unit,
) {
    InformationModificationProposalCompleteScreen(
        moveToContentScreen = moveToContentScreen,
        isContent = true
    )
}

@Composable
private fun InformationModificationProposalCompleteScreen(
    moveToContentScreen: () -> Unit,
    isContent: Boolean
) {
    val rawRes = remember {
        listOf(
            R.raw.info_modify_complete_balloon,
            R.raw.info_modify_complete_speaker
        ).random()
    }
    val gif by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))

    CustomSurface {
        Box{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(80.dp))

                LottieAnimation(
                    modifier = Modifier.size(250.dp, 230.dp),
                    composition = gif,
                    iterations = LottieConstants.IterateForever
                )

                Spacer(Modifier.height(40.dp))


                Text(
                    text = getString(R.string.info_modification_proposal_heading4),
                    color = getColor().main,
                    style = title01Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = getString(R.string.info_modification_proposal_text4),
                    color = getColor().black,
                    style = body01,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(100.dp))
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            ) {
                BottomOkButton(
                    text = getString(R.string.info_modification_proposal_button1),
                    isActivated = true,
                    onClick = moveToContentScreen
                )
            }

        }
    }
}
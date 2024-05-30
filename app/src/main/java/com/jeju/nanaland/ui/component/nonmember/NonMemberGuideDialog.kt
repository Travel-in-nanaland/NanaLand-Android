package com.jeju.nanaland.ui.component.nonmember

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.ui.component.nonmember.parts.NonMemberDialogBottomButton
import com.jeju.nanaland.ui.component.nonmember.parts.NonMemberDialogGuidText
import com.jeju.nanaland.ui.component.nonmember.parts.NonMemberGuideDialogCloseButton
import com.jeju.nanaland.ui.component.nonmember.parts.NonMemberGuideDialogLogo
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun NonMemberGuideDialog(
    onCloseClick: () -> Unit,
    moveToSignInScreen: () -> Unit
) {
    Dialog(
        onDismissRequest = onCloseClick
    ) {
        NanaLandTheme {
            Surface(
                modifier = Modifier.height(340.dp),
                color = getColor().white,
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 16.dp, end = 16.dp)
                    ) {
                        NonMemberGuideDialogCloseButton { onCloseClick() }
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(30.dp))

                        NonMemberGuideDialogLogo()

                        Spacer(Modifier.height(16.dp))

                        NonMemberDialogGuidText()

                        Spacer(Modifier.weight(1f))

                        NonMemberDialogBottomButton { moveToSignInScreen() }

                        Spacer(Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}
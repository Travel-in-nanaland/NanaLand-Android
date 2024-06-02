package com.jeju.nanaland.ui.component.signout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.ui.component.settings.SettingsScreenHorizontalDivider
import com.jeju.nanaland.ui.component.common.SettingsScreenDialogCancelButton
import com.jeju.nanaland.ui.component.common.SettingsScreenDialogConfirmButton
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenDialogVerticalDivider
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold

@Composable
fun SignOutConfirmDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel
    ) {
        NanaLandTheme {
            Surface(
                color = getColor().white,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "정말 로그아웃을\n" +
                                    "하시겠습니까?",
                            color = getColor().black,
                            style = title01Bold
                        )
                    }

                    SettingsScreenHorizontalDivider()

                    Row(Modifier.height(IntrinsicSize.Min)) {
                        SettingsScreenDialogConfirmButton(
                            text = "네",
                            onClick = {
                                onCancel()
                                onConfirm()
                            }
                        )

                        WithdrawalScreenDialogVerticalDivider()

                        SettingsScreenDialogCancelButton(
                            text = "아니오",
                            onClick = onCancel
                        )
                    }
                }
            }
        }
    }
}
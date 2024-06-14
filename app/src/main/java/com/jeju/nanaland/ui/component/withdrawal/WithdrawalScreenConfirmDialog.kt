package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.SettingsScreenDialogCancelButton
import com.jeju.nanaland.ui.component.common.SettingsScreenDialogConfirmButton
import com.jeju.nanaland.ui.component.settings.SettingsScreenHorizontalDivider
import com.jeju.nanaland.ui.component.withdrawal.parts.WithdrawalScreenDialogDescription
import com.jeju.nanaland.ui.component.withdrawal.parts.WithdrawalScreenDialogHeading
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenConfirmDialog(
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
                    .height(220.dp),
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
                        WithdrawalScreenDialogHeading()

                        Spacer(Modifier.height(16.dp))

                        WithdrawalScreenDialogDescription()
                    }

                    SettingsScreenHorizontalDivider()

                    Row(Modifier.height(IntrinsicSize.Min)) {
                        SettingsScreenDialogCancelButton(
                            text = getString(R.string.common_취소),
                            onClick = onCancel
                        )

                        WithdrawalScreenDialogVerticalDivider()

                        SettingsScreenDialogConfirmButton(
                            text = getString(R.string.common_삭제),
                            onClick = {
                                onCancel()
                                onConfirm()
                            }
                        )
                    }
                }
            }
        }
    }
}
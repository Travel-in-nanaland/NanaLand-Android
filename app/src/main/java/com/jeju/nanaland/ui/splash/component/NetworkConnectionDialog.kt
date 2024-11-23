package com.jeju.nanaland.ui.splash.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.SettingsScreenDialogCancelButton
import com.jeju.nanaland.ui.component.common.SettingsScreenDialogConfirmButton
import com.jeju.nanaland.ui.component.settings.SettingsScreenHorizontalDivider
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenDialogVerticalDivider
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun NetworkConnectionDialog(
    exit: () -> Unit,
    retry: () -> Unit,
) {
    Dialog(
        onDismissRequest = {}
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
                        Image(
                            modifier = Modifier.size(38.dp),
                            painter = painterResource(R.drawable.ic_warning_triangle),
                            contentDescription = null
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = getString(R.string.dialog_msg_title_11),
                            color = getColor().black,
                            style = title01Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    SettingsScreenHorizontalDivider()

                    Row(Modifier.height(IntrinsicSize.Min)) {
                        SettingsScreenDialogCancelButton(
                            text = getString(R.string.dialog_btn_out),
                            onClick = { exit() }
                        )

                        WithdrawalScreenDialogVerticalDivider()

                        SettingsScreenDialogConfirmButton(
                            text = getString(R.string.dialog_btn_retry),
                            onClick = { retry() }
                        )
                    }
                }
            }
        }
    }
}
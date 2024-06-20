package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenAgreeText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = getString(R.string.withdrawal_screen_warning),
        color = getColor().black,
        style = body02Bold,
        textAlign = TextAlign.Center
    )
}
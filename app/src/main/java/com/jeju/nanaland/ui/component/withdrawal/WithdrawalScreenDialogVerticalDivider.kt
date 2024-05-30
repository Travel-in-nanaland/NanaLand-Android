package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun WithdrawalScreenDialogVerticalDivider() {
    Spacer(
        modifier = Modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(getColor().gray02)
    )
}
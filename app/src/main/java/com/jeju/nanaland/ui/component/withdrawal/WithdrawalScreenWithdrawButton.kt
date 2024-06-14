package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun WithdrawalScreenWithdrawButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(156.dp)
            .height(48.dp)
            .background(
                color = getColor().gray02,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getString(R.string.withdrawal_screen_탈퇴),
            color = getColor().gray01,
            style = bodyBold
        )
    }
}
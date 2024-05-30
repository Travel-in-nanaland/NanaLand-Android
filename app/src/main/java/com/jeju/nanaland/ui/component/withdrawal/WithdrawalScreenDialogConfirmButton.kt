package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun RowScope.WithdrawalScreenDialogConfirmButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(56.dp)
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "삭제",
            color = getColor().main,
            style = title02Bold
        )
    }
}
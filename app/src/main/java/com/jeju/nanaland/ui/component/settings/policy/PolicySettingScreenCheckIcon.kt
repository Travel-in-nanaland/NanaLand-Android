package com.jeju.nanaland.ui.component.settings.policy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun PolicySettingScreenCheckIcon(isSelected: Boolean) {
    Image(
        modifier = Modifier
            .padding(end = 16.dp)
            .size(24.dp),
        painter = painterResource(if (isSelected) R.drawable.ic_check_rounded_unselected else R.drawable.ic_check_rounded_selected),
        contentDescription = null
    )
}
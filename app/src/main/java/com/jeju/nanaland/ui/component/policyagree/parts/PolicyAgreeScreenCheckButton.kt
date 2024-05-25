package com.jeju.nanaland.ui.component.policyagree.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun PolicyAgreeScreenCheckButton(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(24.dp)
            .clickableNoEffect { onClick() }
        ,
        painter = painterResource(if (isSelected) R.drawable.ic_check_rounded_selected else R.drawable.ic_check_rounded_unselected),
        contentDescription = null
    )
}
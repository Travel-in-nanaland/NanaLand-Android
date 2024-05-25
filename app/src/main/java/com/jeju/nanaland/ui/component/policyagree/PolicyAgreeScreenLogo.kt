package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun PolicyAgreeScreenLogo() {
    Image(
        modifier = Modifier.size(64.dp),
        painter = painterResource(R.drawable.ic_logo_circle),
        contentDescription = null
    )
}
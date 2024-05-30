package com.jeju.nanaland.ui.component.nonmember.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun NonMemberGuideDialogLogo() {
    Image(
        modifier = Modifier.size(80.dp),
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}
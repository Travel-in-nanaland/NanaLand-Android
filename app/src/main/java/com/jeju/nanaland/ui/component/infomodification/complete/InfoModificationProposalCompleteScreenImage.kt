package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun InfoModificationProposalCompleteScreenImage() {
    Image(
        modifier = Modifier.width(250.dp),
        painter = painterResource(R.drawable.img_star),
        contentDescription = null,
    )
}
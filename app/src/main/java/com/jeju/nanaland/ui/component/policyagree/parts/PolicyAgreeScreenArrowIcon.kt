package com.jeju.nanaland.ui.component.policyagree.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun PolicyAgreeScreenArrowIcon() {
    Image(
        modifier = Modifier.size(16.dp),
        painter = painterResource(R.drawable.ic_arrow_right),
        contentDescription = null,
        colorFilter = ColorFilter.tint(getColor().gray01)
    )
}
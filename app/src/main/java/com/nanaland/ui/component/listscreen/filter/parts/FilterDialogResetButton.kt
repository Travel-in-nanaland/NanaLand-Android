package com.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.body01
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun FilterDialogResetButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .clickableNoEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_reset),
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "초기화",
            color = getColor().black,
            style = body01
        )
    }
}
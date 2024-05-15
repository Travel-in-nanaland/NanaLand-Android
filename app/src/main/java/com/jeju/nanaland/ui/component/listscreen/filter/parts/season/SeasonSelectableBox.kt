package com.jeju.nanaland.ui.component.listscreen.filter.parts.season

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SeasonSelectableBox(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickableNoEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = if (isSelected) getColor().main else getColor().black,
            style = if (isSelected) bodyBold else body01
        )

        if (isSelected) {
            Spacer(Modifier.width(16.dp))

            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().main)
            )
        }
    }
}
package com.jeju.nanaland.ui.component.listscreen.filter.parts.season

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SeasonSelectableBox(
    seasonText: String,
    monthText: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                if (isSelected) getColor().main10 else getColor().transparent
            )
            .clickableNoEffect { onClick() }
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = seasonText,
            color = if (isSelected) getColor().main else getColor().black,
            style = if (isSelected) bodyBold else body01
        )

        Text(
            text = monthText,
            color = getColor().gray01,
            style = body02
        )

//        if (isSelected) {
//            Spacer(Modifier.width(16.dp))
//
//            Image(
//                modifier = Modifier.size(20.dp),
//                painter = painterResource(R.drawable.ic_check),
//                contentDescription = null,
//                colorFilter = ColorFilter.tint(getColor().main)
//            )
//        }
    }
}
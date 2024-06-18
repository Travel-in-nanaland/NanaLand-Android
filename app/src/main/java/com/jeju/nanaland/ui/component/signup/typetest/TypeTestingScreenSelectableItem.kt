package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.signup.typetest.parts.TypeTestingScreenItemImage
import com.jeju.nanaland.ui.component.signup.typetest.parts.TypeTestingScreenItemText
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun RowScope.TypeTestingScreenSelectableItem(
    isSelected: Boolean,
    itemIdx: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .clickableNoEffect { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = getColor().main
                    ),
                    shape = RoundedCornerShape(50)
                )
                .background(
                    color = if (isSelected) getColor().main else getColor().white,
                    shape = RoundedCornerShape(50)
                ),
            contentAlignment = Alignment.Center
        ) {
            TypeTestingScreenItemImage(itemIdx)
        }

        Spacer(Modifier.height(16.dp))

        TypeTestingScreenItemText(itemIdx)
    }
}
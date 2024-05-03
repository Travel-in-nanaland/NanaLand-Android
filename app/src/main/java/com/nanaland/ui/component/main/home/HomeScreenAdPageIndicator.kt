package com.nanaland.ui.component.main.home

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.main.home.parts.AdPageIndicatorDot
import com.nanaland.ui.theme.getColor

@Composable
fun HomeScreenAdPageIndicator(
    pageNum: Int
) {
    val transition = updateTransition(pageNum % 3, "")
    val dot1Color: Color by transition.animateColor(label = "") {
        when (it) {
            1 -> getColor().black
            else -> getColor().gray02
        }
    }
    val dot2Color: Color by transition.animateColor(label = "") {
        when (it) {
            2 -> getColor().black
            else -> getColor().gray02
        }
    }
    val dot3Color: Color by transition.animateColor(label = "") {
        when (it) {
            0 -> getColor().black
            else -> getColor().gray02
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        AdPageIndicatorDot(color = dot1Color)

        Spacer(Modifier.width(10.dp))

        AdPageIndicatorDot(color = dot2Color)

        Spacer(Modifier.width(10.dp))

        AdPageIndicatorDot(color = dot3Color)
    }
}
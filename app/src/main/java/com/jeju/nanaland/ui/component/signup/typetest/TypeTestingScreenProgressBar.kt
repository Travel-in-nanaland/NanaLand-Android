package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun TypeTestingScreenProgressBar(level: Int) {
    val animatedWeight by animateFloatAsState(targetValue = level.toFloat(), label = "")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(
                color = getColor().gray02,
                shape = RoundedCornerShape(50)
            )
    ) {
        Spacer(
            modifier = Modifier
                .weight(animatedWeight)
                .fillMaxHeight()
                .background(
                    color = getColor().main,
                    shape = RoundedCornerShape(50)
                )
        )

        Spacer(Modifier.weight(5f - animatedWeight + 0.001f))
    }
}
package com.jeju.nanaland.ui.component.listscreen.filter.parts.location

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun LocationFilterDialogLocationBox(
    locationName: String?,
    isSelected: Boolean,
    updateIsSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(40.dp)
            .border(
                border = BorderStroke(
                    color = if (isSelected) getColor().main50 else getColor().gray02,
                    width = 1.dp
                ),
                shape = RoundedCornerShape(50)
            )
            .background(
                color = if (isSelected) getColor().main10 else Color.Transparent,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { updateIsSelected() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = locationName ?: "",
            color = if (isSelected) getColor().main else getColor().gray01,
            style = when (getLanguage()) {
                "en", "ms" -> body02
                else -> body01
            },
            textAlign = TextAlign.Center
        )
    }
}

@ComponentPreview
@Composable
private fun LocationFilterDialogLocationBoxPreview() {
    NanaLandTheme {
        LocationFilterDialogLocationBox(
            locationName = "제주",
            isSelected = true,
            updateIsSelected = {}
        )
    }
}
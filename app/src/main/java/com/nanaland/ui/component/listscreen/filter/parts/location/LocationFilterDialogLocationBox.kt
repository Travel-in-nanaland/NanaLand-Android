package com.nanaland.ui.component.listscreen.filter.parts.location

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
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body01
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

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
            color = getColor().gray01,
            style = body01
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
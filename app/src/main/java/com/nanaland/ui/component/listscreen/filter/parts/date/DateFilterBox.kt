package com.nanaland.ui.component.listscreen.filter.parts.date

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.caption01
import com.nanaland.ui.theme.getColor
import com.nanaland.util.string.getMonthDateDayOfWeek
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect
import java.util.Calendar

@Composable
fun DateFilterBox(
    showDimBackground: () -> Unit,
    openDateFilterDialog: () -> Unit,
    startCalendar: Calendar?,
    endCalendar: Calendar?,
) {
    val borderColor = getColor().gray02
    Row(
        modifier = Modifier
            .height(32.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect {
                showDimBackground()
                openDateFilterDialog()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.width(12.dp))

        Text(
            text = if (startCalendar != null && endCalendar != null) "${getMonthDateDayOfWeek(startCalendar)} ~ ${getMonthDateDayOfWeek(endCalendar)}" else "날짜",
            color = getColor().gray01,
            style = caption01
        )

        Spacer(Modifier.width(4.dp))

        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.ic_down),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(12.dp))
    }
}

@ComponentPreview
@Composable
private fun DateFilterBoxPreview() {
    NanaLandTheme {
        DateFilterBox(
            showDimBackground = {},
            openDateFilterDialog = {},
            startCalendar = null,
            endCalendar = null
        )
    }
}
package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun NanaPickContentSubContentAdditionalInfo(
    @DrawableRes drawableId: Int,
    infoKey: String?,
    infoValue: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(drawableId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "${infoKey ?: ""} : ${infoValue ?: ""}",
            color = getColor().gray01,
            style = body02
        )
    }
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentAdditionalInfoPreview() {
    NanaLandTheme {
        NanaPickContentSubContentAdditionalInfo(
            drawableId = R.drawable.ic_car_outlined,
            infoKey = "key",
            infoValue = "value"
        )
    }
}
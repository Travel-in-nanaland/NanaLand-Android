package com.jeju.nanaland.ui.component.detailscreen.nanapick

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.appleSdGothicNeo
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.nanumPen
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.drawColoredShadow

@Composable
fun NanaPickContentAttractivePoint() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .drawColoredShadow(
                color = getColor().black,
                widthControl = (-10).dp,
                heightControl = (-30).dp,
                alpha = 1f
            )
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(50)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(16.dp))

        Text(
            text = getString(R.string.detail_screen_common_이_장소만의_매력_포인트),
            fontFamily = when (getLanguage()) {
                "zh" -> appleSdGothicNeo
                else -> nanumPen
            },
            fontSize = 20.sp,
            color = getColor().main
        )

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(16.dp))
    }
}
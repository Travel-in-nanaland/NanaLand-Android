package com.jeju.nanaland.ui.component.detailscreen.other

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.text.TextWithIntent
import com.jeju.nanaland.ui.component.detailscreen.other.parts.information.DetailScreenInformationIcon
import com.jeju.nanaland.ui.component.detailscreen.other.parts.information.DetailScreenInformationTitle
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun DetailScreenInformation(
    @DrawableRes drawableId: Int,
    title: String?,
    content: String?,
    moveToMap: (()-> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        DetailScreenInformationIcon(id = drawableId)

        Spacer(Modifier.width(8.dp))

        Column {
            DetailScreenInformationTitle(text = title)

            Spacer(Modifier.height(4.dp))

            TextWithIntent(
                text = content ?: "",
                color = getColor().black,
                style = body02
            )
            moveToMap?.let {
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .clickableNoEffect(moveToMap)
                        .background(
                            color = getColor().gray03,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getString(R.string.map_view_map),
                        style = caption01,
                        color = getColor().gray01
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = getColor().gray01
                    )
                }
            }
        }
    }
}

@ScreenPreview
@Composable
private fun DetailScreenInformationPreview() {
    NanaLandTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            DetailScreenInformation(
                drawableId = R.drawable.ic_heart_outlined,
                title = "Title",
                content = "Content"
            )
        }
    }
}
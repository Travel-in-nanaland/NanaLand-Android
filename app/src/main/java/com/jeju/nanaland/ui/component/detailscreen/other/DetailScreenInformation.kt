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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.text.TextWithIntent
import com.jeju.nanaland.ui.component.detailscreen.other.parts.information.DetailScreenInformationIcon
import com.jeju.nanaland.ui.component.detailscreen.other.parts.information.DetailScreenInformationTitle
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun DetailScreenInformation(
    @DrawableRes drawableId: Int,
    title: String?,
    content: String?,
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
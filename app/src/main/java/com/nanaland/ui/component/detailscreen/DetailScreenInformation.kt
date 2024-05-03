package com.nanaland.ui.component.detailscreen

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
import com.nanaland.R
import com.nanaland.ui.component.detailscreen.parts.information.DetailScreenInformationContent
import com.nanaland.ui.component.detailscreen.parts.information.DetailScreenInformationIcon
import com.nanaland.ui.component.detailscreen.parts.information.DetailScreenInformationTitle
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ScreenPreview

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

            DetailScreenInformationContent(text = content)
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
                drawableId = R.drawable.ic_heart_outlined_black,
                title = "Title",
                content = "Content"
            )
        }
    }
}
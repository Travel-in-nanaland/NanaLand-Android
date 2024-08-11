package com.jeju.nanaland.ui.component.detailscreen.nanapick

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.language.customContext
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview


@Composable
@Preview
private fun NanaPickContentAttractivePointDialogPreview() {

}

@Composable
fun NanaPickContentAttractivePointDialog(
    text: String,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        NanaLandTheme {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(getColor().white)
                    .padding(16.dp)
            ) {
                Column {
                    Row {
                        TitleText()

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { onDismiss() },
                            painter =  painterResource(id = R.drawable.ic_close),
                            contentDescription =  null,
                            tint = getColor().black,
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = text,
                        color = getColor().black,
                        style = body02
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun TitleText() {
    val titleLineColor = getColor().main10

    Text(
        modifier = Modifier
            .drawBehind {
                val rectTopOffset = size.height / 2
                val rectStartOffset =
                    size.width * when (customContext.resources.configuration.locales[0].language) {
                        "ko" -> 0.438f
                        "ms" -> 0f
                        "zh" -> 0.6f
                        else -> 0f
                    }

                val rectHeightPx = size.height * 2 / 3
                val rectWidthPx =
                    size.width * when (customContext.resources.configuration.locales[0].language) {
                        "ko" -> 0.4666f
                        "ms" -> 0.4978f
                        "zh" -> 0.2437f
                        else -> 0.4238f
                    }

                drawRect(
                    color = titleLineColor,
                    topLeft = Offset(rectStartOffset, rectTopOffset),
                    size = Size(
                        width = rectWidthPx,
                        height = rectHeightPx
                    )
                )
            }
            .padding(top = 8.dp),
        text = getString(R.string.detail_screen_common_이_장소만의_매력_포인트),
        color = getColor().black,
        style = title01Bold
    )
}
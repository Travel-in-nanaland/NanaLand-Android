package com.jeju.nanaland.ui.component.detailscreen.nanapick

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.resource.getString


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
                        TitleText(getString(R.string.detail_screen_common_이_장소만의_매력_포인트))

                        Spacer(modifier = Modifier.width(32.dp))

                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { onDismiss() }
                                .padding(3.dp),
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RowScope.TitleText(str: String, splitStr: String = "{}") {
    val sliceString = remember(str, splitStr) { str.split(*splitStr.toCharArray()) }

    FlowRow(
        modifier = Modifier.weight(1f)
    ) {
        sliceString.forEachIndexed { index, s ->
            if(index % 2 == 1)
                TitleText_Highlight(s)
            else
                TitleText_Normal(s)
        }
    }
}

@Composable
private fun TitleText_Normal(str: String) {
    Text(
        text = str,
        color = getColor().black,
        style = title01Bold
    )
}

@Composable
private fun TitleText_Highlight(str: String) {
    var sliceStrings by remember(str) { mutableStateOf(listOf<String>()) }
    val density = LocalDensity.current
    val colorPaddingPx = with(density) { 3.dp.toPx() }

    if(sliceStrings.isEmpty())
        Text(
            text = str,
            style = title01Bold,
            onTextLayout = {
                val updateSliceStrings = mutableListOf<String>()
                for(i in 0 until it.lineCount){
                    updateSliceStrings.add(
                        str.substring(it.getLineStart(i), it.getLineEnd(i)),
                    )
                }

                sliceStrings = updateSliceStrings
            },
        )
    sliceStrings.forEach {
        TitleText_Highlight(it, density, colorPaddingPx)
    }
}

@Composable
private fun TitleText_Highlight(
    str: String,
    density: Density,
    colorPaddingPx: Float,
    color: Color = getColor().main10,
    ) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

        Text(
            text = str,
            color = getColor().black,
            style = title01Bold,
            onTextLayout = {
                if(textLayoutResult == null)
                    textLayoutResult = it
            },
            modifier = Modifier
                .drawBehind {
                    textLayoutResult?.let {
                        val halfHeight = with(density) { it.size.height.toDp().toPx() / 2 }
                        val width = with(density) { it.size.width.toDp().toPx() }

                        drawRect(
                            color = color,
                            topLeft = Offset(-colorPaddingPx, halfHeight),
                            size = Size(width + colorPaddingPx*2, halfHeight  + colorPaddingPx)
                        )
                    }
                }
        )
}

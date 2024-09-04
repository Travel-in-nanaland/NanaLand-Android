package com.jeju.nanaland.ui.component.common.text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@ComponentPreview
@Composable
private fun PreviewTextWithPointColor(){
    TextWithPointColor("그냥문자{색깔문자}그냥문자")
}

@Composable
fun TextWithPointColor(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = getColor().black,
    style: TextStyle = LocalTextStyle.current,
    delimiter: String = "{}",
    pointColor: Color = getColor().main,
    pointStyle: TextStyle = style,
    ) {
    val sliceString = remember(text) {
        text.split(*delimiter.toCharArray()).also {
            if(it.size % 2 == 0) throw Exception("서식 문자가 잘못된 거 같습니다람쥐")
        }
    }

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            sliceString.forEachIndexed { index, s ->
                if(index % 2 == 1)
                    withStyle(
                        style = pointStyle
                            .copy(color = pointColor)
                            .toSpanStyle()
                    ) {
                        append(s)
                    }
                else
                    append(s)
            }
        },
        color = color,
        style = style
    )
}

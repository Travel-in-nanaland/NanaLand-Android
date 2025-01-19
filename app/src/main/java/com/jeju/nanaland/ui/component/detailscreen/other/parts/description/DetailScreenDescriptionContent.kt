package com.jeju.nanaland.ui.component.detailscreen.other.parts.description

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenDescriptionContent(
    isMoreOpen: MutableState<Boolean>,
    isOverflow: MutableState<Boolean>,
    text: String?
) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02,
        maxLines = if (isMoreOpen.value) Int.MAX_VALUE else 3,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult: TextLayoutResult ->
            if (textLayoutResult.hasVisualOverflow) {
                isOverflow.value = true
            }
        }
    )
}

@ComponentPreview
@Composable
private fun DetailScreenDescriptionContentPreview1() {
    NanaLandTheme {
//        DetailScreenDescriptionContent(
//            isMoreOpen = true,
//            text = "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription"
//        )
    }
}

@ComponentPreview
@Composable
private fun DetailScreenDescriptionContentPreview2() {
    NanaLandTheme {
//        DetailScreenDescriptionContent(
//            isMoreOpen = false,
//            text = "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription"
//        )
    }
}
package com.jeju.nanaland.ui.component.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    collapsedMaxLine: Int = 2,
    showMoreText: String = "더보기",
    showMoreStyle: TextStyle = TextStyle(),
    showLessText: String = "줄이기",
    showLessStyle: TextStyle = showMoreStyle,
    onExpanded: (Boolean) -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }
    Box(modifier) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .clickable(clickable) {
                    isExpanded = !isExpanded
                    onExpanded(isExpanded)
                }
                .animateContentSize(),
            text = if(!clickable || isExpanded) {
                buildAnnotatedString {
                    append(text)
                    withStyle(showLessStyle.copy(Color.Transparent).toSpanStyle()){
                        append(showLessText)
                    }
                }
            }
            else {
                buildAnnotatedString {
                    append(
                        text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length + 3)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                            .plus("...")
                    )
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
        )

        if(clickable)
            Text(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = if(isExpanded) showLessText else showMoreText,
                style = if(isExpanded) showLessStyle else showMoreStyle
            )
    }

}
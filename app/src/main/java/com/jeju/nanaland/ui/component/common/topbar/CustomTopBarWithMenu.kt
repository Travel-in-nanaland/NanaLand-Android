package com.jeju.nanaland.ui.component.common.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawColoredShadow

@Preview
@Composable
private fun CustomTopBarWithMenuPreview(){
    CustomTopBarWithMenu("Title", true, {}, { Icon(Icons.Default.Menu, null)})
}

@Composable
fun CustomTopBarWithMenu(
    title: String = "",
    drawShadow: Boolean = true,
    onBackButtonClicked: (() -> Unit)? = null,
    menus: @Composable RowScope.() -> Unit = { }
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT.dp)
            .clip(
                GenericShape { size, _ ->
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height + 100f)
                    lineTo(0f, size.height + 100f)
                }
            )
            .zIndex(10f)
            .drawColoredShadow(
                color = getColor().black,
                alpha = if (drawShadow) 0.1f else 0f,
                shadowRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            )
            .background(getColor().white)
            .padding(horizontal = 16.dp)
    ) {
        if(onBackButtonClicked != null)
            Image(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickableNoEffect { onBackButtonClicked() }
                    .size(32.dp),
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().black)
            )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            color = getColor().black,
            style = title01Bold,
            textAlign = TextAlign.Center
        )

        Row(Modifier.align(Alignment.CenterEnd)) {
            menus()
        }
    }
}
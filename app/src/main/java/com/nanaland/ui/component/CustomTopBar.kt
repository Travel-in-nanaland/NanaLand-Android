package com.nanaland.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title01Bold
import com.nanaland.util.ui.CustomPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackButtonClicked: () -> Unit,
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
            .then(modifier)
            .background(getColor().white)
        ,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(50))
                .clickableNoEffect { onBackButtonClicked() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().black)
            )
        }
        Text(
            text = title,
            color = getColor().black,
            style = title01Bold
        )
    }
}

@CustomPreview
@Composable
private fun CustomTopBarPreview() {
    CustomTopBar(
        title = "나나 Pick",
        onBackButtonClicked = {}
    )
}
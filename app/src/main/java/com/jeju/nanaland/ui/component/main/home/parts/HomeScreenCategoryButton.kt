package com.jeju.nanaland.ui.component.main.home.parts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun HomeScreenCategoryButton(
    @DrawableRes resId: Int,
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickableNoEffect { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(56.dp)
                .height(48.dp),
            painter = painterResource(id = resId),
            contentDescription = null
        )
        Text(
            text = text,
            color = Color(0xFF1A1A1A),
            style = caption01SemiBold,
            textAlign = TextAlign.Center
        )
    }
}
package com.jeju.nanaland.ui.component.detailscreen.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawCircleColoredShadow


@Composable
fun BoxScope.MoveToTopButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .drawCircleColoredShadow(
                    color = Color.Black,
                    alpha = 0.3f,
                    shadowRadius = 10.dp
                )
                .clip(CircleShape)
        )
        Image(
            modifier = Modifier
                .size(56.dp)
                .clickableNoEffect { onClick() },
            painter = painterResource(id = R.drawable.ic_button_up),
            contentDescription = null
        )
    }
}

@ComponentPreview
@Composable
private fun MoveToTopButtonPreview() {
    NanaLandTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            MoveToTopButton(onClick = {})
        }
    }
}

package com.nanaland.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch


@Composable
fun BoxScope.MoveToTopButton(
    moveToTop: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Image(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 20.dp, bottom = 20.dp)
            .size(80.dp)
            .clickableNoEffect {
                coroutineScope.launch {
                    moveToTop()
                }
            },
        painter = painterResource(id = R.drawable.ic_button_up),
        contentDescription = null
    )
}
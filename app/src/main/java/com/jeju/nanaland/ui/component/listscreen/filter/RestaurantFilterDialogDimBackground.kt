package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RestaurantFilterDialogDimBackground(
    isDimBackgroundShowing: MutableState<Boolean>,
    locationAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>? = null,
    keywordAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>? = null,
) {
    val density = LocalDensity.current.density
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((TOTAL_SCREEN_HEIGHT * density).dp)
            .offset {
                IntOffset(x = 0, y = (-SYSTEM_STATUS_BAR_HEIGHT * density).roundToInt())
            }
            .alpha(
                ((locationAnchoredDraggableState?.anchors?.maxAnchor() ?: 0f) - (locationAnchoredDraggableState?.offset ?: 0f)) /
                        ((locationAnchoredDraggableState?.anchors?.maxAnchor() ?: 1f) - (locationAnchoredDraggableState?.anchors?.minAnchor() ?: 0f)) +
                        ((keywordAnchoredDraggableState?.anchors?.maxAnchor() ?: 0f) - (keywordAnchoredDraggableState?.offset ?: 0f)) /
                        ((keywordAnchoredDraggableState?.anchors?.maxAnchor() ?: 1f) - (keywordAnchoredDraggableState?.anchors?.minAnchor() ?: 0f))
            )
            .background(color = Color(0xAA000000))
            .clickableNoEffect {
                coroutineScope.launch {
                    if (locationAnchoredDraggableState?.targetValue == AnchoredDraggableContentState.Open) {
                        locationAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    }
                    if (keywordAnchoredDraggableState?.targetValue == AnchoredDraggableContentState.Open) {
                        keywordAnchoredDraggableState.animateTo(
                            AnchoredDraggableContentState.Closed)
                    }
                    isDimBackgroundShowing.value = false
                }
            }
    )
}
package com.nanaland.ui.component.listscreen.filter

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
import com.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilterDialogDimBackground(
    isDimBackgroundShowing: MutableState<Boolean>,
    dateAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>? = null,
    locationAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>? = null,
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
                if (dateAnchoredDraggableState != null && locationAnchoredDraggableState != null) {
                    (locationAnchoredDraggableState.anchors.maxAnchor() - locationAnchoredDraggableState.offset) /
                            (locationAnchoredDraggableState.anchors.maxAnchor() - locationAnchoredDraggableState.anchors.minAnchor()) +
                            (dateAnchoredDraggableState.anchors.maxAnchor() - dateAnchoredDraggableState.offset) /
                            (dateAnchoredDraggableState.anchors.maxAnchor() - dateAnchoredDraggableState.anchors.minAnchor())
                } else if (dateAnchoredDraggableState != null && locationAnchoredDraggableState == null) {
                    (dateAnchoredDraggableState.anchors.maxAnchor() - dateAnchoredDraggableState.offset) /
                            (dateAnchoredDraggableState.anchors.maxAnchor() - dateAnchoredDraggableState.anchors.minAnchor())
                } else if (dateAnchoredDraggableState == null && locationAnchoredDraggableState != null) {
                    (locationAnchoredDraggableState.anchors.maxAnchor() - locationAnchoredDraggableState.offset) /
                            (locationAnchoredDraggableState.anchors.maxAnchor() - locationAnchoredDraggableState.anchors.minAnchor())
                } else {
                    1f
                }
            )
            .background(color = Color(0xAA000000))
            .clickableNoEffect {
                coroutineScope.launch {
                    if (dateAnchoredDraggableState != null && locationAnchoredDraggableState != null) {
                        if (locationAnchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
                            locationAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                        } else {
                            dateAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                        }
                    } else if (dateAnchoredDraggableState != null && locationAnchoredDraggableState == null) {
                        if (dateAnchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
                            dateAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                        }
                    } else if (dateAnchoredDraggableState == null && locationAnchoredDraggableState != null) {
                        if (locationAnchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
                            locationAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                        }
                    }
                    isDimBackgroundShowing.value = false
                }
            }
    )
}
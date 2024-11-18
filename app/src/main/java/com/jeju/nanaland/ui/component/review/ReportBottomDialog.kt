package com.jeju.nanaland.ui.component.review

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogCloseButton
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReportBottomDialog(
    onClick: () -> Unit,
    hideDimBackground: () -> Unit,
    anchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>
) {

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp)
            .offset {
                IntOffset(
                    x = 0,
                    y = (anchoredDraggableState
                        .requireOffset()
                        .roundToInt() * density).roundToInt()
                )
            }
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
            .clickableNoEffect {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .padding(top = 16.dp, end = 16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            FilterDialogCloseButton {
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    hideDimBackground()
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .clickableNoEffect { onClick() },
            text = getString(R.string.common_신고하기), //TODO
            color = getColor().black,
            style = body01
        )
    }
}

// anchoredDraggableState의 offset은 pixel단위로 측정됨.
// y offset이 0일 때 statusbar 바로 아래부터 시작됨.
@OptIn(ExperimentalFoundationApi::class)
fun getReportAnchoredDraggableState() = AnchoredDraggableState(
    initialValue = AnchoredDraggableContentState.Closed,
    positionalThreshold = { it: Float -> it * 0.5f },
    velocityThreshold = { 100f },
    snapAnimationSpec = tween(400),
    decayAnimationSpec = exponentialDecay(400f),
).apply {
    updateAnchors(
        DraggableAnchors {
            AnchoredDraggableContentState.Open at TOTAL_SCREEN_HEIGHT - 100f - SYSTEM_STATUS_BAR_HEIGHT - SYSTEM_NAVIGATION_BAR_HEIGHT
            AnchoredDraggableContentState.Closed at TOTAL_SCREEN_HEIGHT - SYSTEM_STATUS_BAR_HEIGHT
        }
    )
}
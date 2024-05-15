package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogCloseButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.season.SeasonFilterDialogTitle
import com.jeju.nanaland.ui.component.listscreen.filter.parts.season.SeasonSelectableBox
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SeasonFilterBottomDialog(
    seasonList: List<String>,
    hideDimBackground: () -> Unit,
    anchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    selectedSeasonList: SnapshotStateList<Boolean>,
    updateList: () -> Unit,
    clearList: () -> Unit,
) {
    val density = LocalDensity.current.density
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .offset {
                IntOffset(
                    x = 0,
                    y = (anchoredDraggableState.requireOffset().roundToInt() * density).roundToInt()
                )
            }
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
            .padding(start = 16.dp, end = 16.dp)
            .clickableNoEffect {},
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            SeasonFilterDialogTitle()

            Spacer(Modifier.weight(1f))

            FilterDialogCloseButton {
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    hideDimBackground()
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        repeat(4) { seasonIdx ->
            SeasonSelectableBox(
                text = seasonList[seasonIdx],
                onClick = {
                    // 이미 같은 계절이 선택되어 있으면 새로고침 안한다.
                    if (!selectedSeasonList[seasonIdx]) {
                        repeat(4) { selectedSeasonList[it] = false }
                        selectedSeasonList[seasonIdx] = true
                        clearList()
                        updateList()
                    }
                    coroutineScope.launch {
                        anchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                        hideDimBackground()
                    }
                },
                isSelected = selectedSeasonList[seasonIdx]
            )
        }
    }
}

// anchoredDraggableState의 offset은 pixel단위로 측정됨.
// y offset이 0일 때 statusbar 바로 아래부터 시작됨.
@OptIn(ExperimentalFoundationApi::class)
fun getSeasonAnchoredDraggableState() = AnchoredDraggableState(
    initialValue = AnchoredDraggableContentState.Closed,
    positionalThreshold = { it: Float -> it * 0.5f },
    velocityThreshold = { 100f },
    animationSpec = tween(400)
).apply {
    updateAnchors(
        DraggableAnchors {
            AnchoredDraggableContentState.Open at TOTAL_SCREEN_HEIGHT - 300f - SYSTEM_STATUS_BAR_HEIGHT - SYSTEM_NAVIGATION_BAR_HEIGHT
            AnchoredDraggableContentState.Closed at TOTAL_SCREEN_HEIGHT - SYSTEM_STATUS_BAR_HEIGHT
        }
    )
}
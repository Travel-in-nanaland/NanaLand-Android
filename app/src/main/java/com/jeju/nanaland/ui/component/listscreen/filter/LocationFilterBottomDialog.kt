package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.jeju.nanaland.globalvalue.constant.getLocationSelectionList
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogApplyButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogCloseButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogSelectedCount
import com.jeju.nanaland.ui.component.listscreen.filter.parts.JejuMapImage
import com.jeju.nanaland.ui.component.listscreen.filter.parts.JejuMapNoticeText
import com.jeju.nanaland.ui.component.listscreen.filter.parts.LocationFilterDialogResetButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.LocationFilterDialogSelectAllButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.location.LocationFilterDialogLocationBox
import com.jeju.nanaland.ui.component.listscreen.filter.parts.location.LocationFilterDialogTitle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationFilterBottomDialog(
    locationList: List<String>,
    hideDimBackground: () -> Unit,
    anchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    selectedLocationList: SnapshotStateList<Boolean>,
    updateList: () -> Unit,
    clearList: () -> Unit
) {
    val tmpSelectedLocationList = remember { getLocationSelectionList() }
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current.density
    LaunchedEffect(anchoredDraggableState.targetValue) {
        if (anchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
            tmpSelectedLocationList.forEachIndexed { idx, _ -> tmpSelectedLocationList[idx] = selectedLocationList[idx] }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(630.dp)
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
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            LocationFilterDialogTitle()

            Spacer(Modifier.width(8.dp))

            FilterDialogSelectedCount(
                count = tmpSelectedLocationList.count { it },
                maxCount = 14
            )

            Spacer(Modifier.weight(1f))

            FilterDialogCloseButton {
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    hideDimBackground()
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        JejuMapImage(
            selectedLocationList = tmpSelectedLocationList
        )

        JejuMapNoticeText()

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                columns = GridCells.Fixed(4)
            ) {
                itemsIndexed(locationList) { idx, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                            .height(56.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LocationFilterDialogLocationBox(
                            locationName = item,
                            isSelected = tmpSelectedLocationList[idx],
                            updateIsSelected = { tmpSelectedLocationList[idx] = !tmpSelectedLocationList[idx] }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(48.dp)
        ) {
            LocationFilterDialogSelectAllButton {
                tmpSelectedLocationList.forEachIndexed { idx, _ ->
                    tmpSelectedLocationList[idx] = true
                }
            }

            Spacer(Modifier.weight(1f))

            LocationFilterDialogResetButton {
                tmpSelectedLocationList.forEachIndexed { idx, _ ->
                    tmpSelectedLocationList[idx] = false
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(Modifier.padding(start = 16.dp, end = 16.dp)) {
            FilterDialogApplyButton {
                val differentCount = selectedLocationList.zip(tmpSelectedLocationList).count { (x, y) -> x != y }
                if (differentCount != 0) {
                    tmpSelectedLocationList.forEachIndexed { idx, _ ->
                        selectedLocationList[idx] = tmpSelectedLocationList[idx]
                    }
                    clearList()
                    updateList()
                }
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    hideDimBackground()
                }
            }
        }

        Spacer(Modifier.height(10.dp))
    }
}

// anchoredDraggableState의 offset은 pixel단위로 측정됨.
// y offset이 0일 때 statusbar 바로 아래부터 시작됨.
@OptIn(ExperimentalFoundationApi::class)
fun getLocationAnchoredDraggableState() = AnchoredDraggableState(
    initialValue = AnchoredDraggableContentState.Closed,
    positionalThreshold = { it: Float -> it * 0.5f },
    velocityThreshold = { 100f },
    snapAnimationSpec = tween(400),
    decayAnimationSpec = exponentialDecay(400f),
).apply {
    updateAnchors(
        DraggableAnchors {
            AnchoredDraggableContentState.Open at TOTAL_SCREEN_HEIGHT - 628f - SYSTEM_STATUS_BAR_HEIGHT - SYSTEM_NAVIGATION_BAR_HEIGHT
            AnchoredDraggableContentState.Closed at TOTAL_SCREEN_HEIGHT - SYSTEM_STATUS_BAR_HEIGHT
        }
    )
}
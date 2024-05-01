package com.nanaland.ui.festival

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.nanaland.globalvalue.type.FestivalCategoryType
import com.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.nanaland.ui.component.CustomSurface
import com.nanaland.ui.component.CustomTopBar
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body01
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.bodyBold
import com.nanaland.ui.theme.caption01
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.largeTitle02
import com.nanaland.util.daterangecalendar.customviews.AdapterEventCalendarMonths
import com.nanaland.util.daterangecalendar.customviews.CalendarDateRangeManagerImpl
import com.nanaland.util.daterangecalendar.customviews.CalendarListener
import com.nanaland.util.daterangecalendar.customviews.DateRangeCalendarView
import com.nanaland.util.string.getMonthDateDayOfWeek
import com.nanaland.util.ui.CustomPreview
import com.nanaland.util.ui.clickableNoEffect
import com.nanaland.util.ui.drawColoredShadow
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.math.roundToInt

@Composable
fun FestivalListScreen(
    moveToFestivalContentScreen: () -> Unit,
    viewModel: FestivalListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    val selectedLocationList = viewModel.selectedLocationList
    val startCalendar = viewModel.startCalendar.collectAsState().value
    val endCalendar = viewModel.endCalendar.collectAsState().value
    FestivalListScreen(
        selectedCategoryType = selectedCategoryType,
        updateSelectedCategoryType = viewModel::updateSelectedCategoryType,
        startCalendar = startCalendar,
        updateStartCalendar = viewModel::updateStartCalendar,
        endCalendar = endCalendar,
        updateEndCalendar = viewModel::updateEndCalendar,
        selectedLocationList = selectedLocationList,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FestivalListScreen(
    selectedCategoryType: FestivalCategoryType,
    updateSelectedCategoryType: (FestivalCategoryType) -> Unit,
    startCalendar: Calendar?,
    updateStartCalendar: (Calendar?) -> Unit,
    endCalendar: Calendar?,
    updateEndCalendar: (Calendar?) -> Unit,
    selectedLocationList: SnapshotStateList<Boolean>,
    isContent: Boolean
) {
    val dateSelectionContentAnchoredDraggableState = remember { dateAnchoredDraggableState }
    val locationSelectionContentAnchoredDraggableState = remember { locationAnchoredDraggableState }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val tmpSelectedLocationList = remember {
        val list = mutableStateListOf<Boolean>()
        list.addAll(selectedLocationList)
        list
    }
    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBar(
                    modifier = Modifier.drawColoredShadow(
                        color = getColor().black,
                        alpha = 0.1f,
                        shadowRadius = 10.dp,
                        offsetX = 0.dp,
                        offsetY = 0.dp
                    ),
                    title = "축제",
                    onBackButtonClicked = {  }
                )

                Spacer(Modifier.height(16.dp))

                CategoryListTab(
                    selectedCategoryType = selectedCategoryType,
                    updateSelectedCategoryType = updateSelectedCategoryType
                )

                when (selectedCategoryType) {
                    FestivalCategoryType.Monthly -> {
                        MonthlyFilterTab(
                            selectedLocationList = selectedLocationList,
                            locationList = locationList,
                            dateAnchoredDraggableState = dateSelectionContentAnchoredDraggableState,
                            locationAnchoredDraggableState = locationSelectionContentAnchoredDraggableState,
                            initTmpSelectedLocationList = {
                                tmpSelectedLocationList.clear()
                                tmpSelectedLocationList.addAll(selectedLocationList)
                            },
                            isDimBackgroundShowing = isDimBackgroundShowing,
                            startCalendar = startCalendar,
                            endCalendar = endCalendar,
                        )

                        SearchResultList(

                        )
                    }
                    FestivalCategoryType.Ended -> {

                    }
                    FestivalCategoryType.Seasonal -> {

                    }
                }
            }
            if (isDimBackgroundShowing.value) {
                DimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    dateAnchoredDraggableState = dateSelectionContentAnchoredDraggableState,
                    locationAnchoredDraggableState = locationSelectionContentAnchoredDraggableState
                )
            }

            DateSelectionContent(
                anchoredDraggableState = dateSelectionContentAnchoredDraggableState,
                isDimBackgroundShowing = isDimBackgroundShowing,
                updateStartCalendar = updateStartCalendar,
                updateEndCalendar = updateEndCalendar
            )

            LocationSelectionContent(
                locationList =locationList,
                isDimBackgroundShowing = isDimBackgroundShowing,
                anchoredDraggableState = locationSelectionContentAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                tmpSelectedLocationList = tmpSelectedLocationList,
            )
        }
    }
}

@Composable
private fun CategoryListTab(
    selectedCategoryType: FestivalCategoryType,
    updateSelectedCategoryType: (FestivalCategoryType) -> Unit,
) {
    val mainColor = getColor().main
    val titleList = remember {
        listOf(FestivalCategoryType.Monthly, FestivalCategoryType.Ended, FestivalCategoryType.Seasonal)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickableNoEffect { updateSelectedCategoryType(titleList[it]) }
                    .drawBehind {
                        if (titleList[it] == selectedCategoryType) {
                            val borderSize = 2.dp.toPx()
                            drawLine(
                                color = mainColor,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = borderSize
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (it) {
                        0 -> "월별 축제"
                        1 -> "종료된 축제"
                        else -> "계절별 축제"
                    },
                    color = Color(0xFF000000),
                    style = caption01
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MonthlyFilterTab(
    selectedLocationList: SnapshotStateList<Boolean>,
    locationList: List<String>,
    dateAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    locationAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    initTmpSelectedLocationList: () -> Unit,
    isDimBackgroundShowing: MutableState<Boolean>,
    startCalendar: Calendar?,
    endCalendar: Calendar?,
) {
    val borderColor = getColor().gray02
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "2건",
            color = getColor().gray01,
            style = body02
        )

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .height(32.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(50)
                )
                .clickableNoEffect {
                    isDimBackgroundShowing.value = true
                    coroutineScope.launch {
                        dateAnchoredDraggableState.animateTo(
                            AnchoredDraggableContentState.Open
                        )
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.width(12.dp))

            Text(
                text = if (startCalendar != null && endCalendar != null) "${getMonthDateDayOfWeek(startCalendar)} ~ ${getMonthDateDayOfWeek(endCalendar)}" else "날짜",
                color = getColor().gray01,
                style = caption01
            )

            Spacer(Modifier.width(4.dp))

            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_down),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().gray01)
            )

            Spacer(Modifier.width(12.dp))
        }

        Spacer(Modifier.width(16.dp))

        Row(
            modifier = Modifier
                .height(32.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(50)
                )
                .clickableNoEffect {
                    initTmpSelectedLocationList()
                    isDimBackgroundShowing.value = true
                    coroutineScope.launch {
                        locationAnchoredDraggableState.animateTo(
                            AnchoredDraggableContentState.Open
                        )
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.width(12.dp))

            Text(
                text = when (selectedLocationList.count { it }) {
                    0 -> "지역"
                    1, 2 -> selectedLocationList.withIndex()
                        .filter { selectedLocationList[it.index] }.take(2)
                        .joinToString(separator = ", ") { locationList[it.index] }
                    else -> selectedLocationList.withIndex()
                        .filter { selectedLocationList[it.index] }.take(2)
                        .joinToString(separator = ", ") { locationList[it.index] } + ", ..."
                },
                color = getColor().gray01,
                style = caption01
            )

            Spacer(Modifier.width(4.dp))

            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_down),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().gray01)
            )

            Spacer(Modifier.width(12.dp))
        }
    }
}

@Composable
private fun SearchResultList() {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DimBackground(
    isDimBackgroundShowing: MutableState<Boolean>,
    dateAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    locationAnchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
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
                (locationAnchoredDraggableState.anchors.maxAnchor() - locationAnchoredDraggableState.offset) /
                        (locationAnchoredDraggableState.anchors.maxAnchor() - locationAnchoredDraggableState.anchors.minAnchor()) +
                        (dateAnchoredDraggableState.anchors.maxAnchor() - dateAnchoredDraggableState.offset) /
                        (dateAnchoredDraggableState.anchors.maxAnchor() - dateAnchoredDraggableState.anchors.minAnchor())
            )
            .background(color = Color(0xAA000000))
            .clickableNoEffect {
                coroutineScope.launch {
                    if (locationAnchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
                        locationAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    } else {
                        dateAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    }
                    isDimBackgroundShowing.value = false
                }
            }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DateSelectionContent(
    anchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    isDimBackgroundShowing: MutableState<Boolean>,
    updateStartCalendar: (Calendar?) -> Unit,
    updateEndCalendar: (Calendar?) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val dateRangeCalendarManager = remember { mutableStateOf<CalendarDateRangeManagerImpl?>(null) }
    val adapterEventCalendarMonths = remember { mutableStateOf<AdapterEventCalendarMonths?>(null) }
    LaunchedEffect(anchoredDraggableState.targetValue) {
        if (anchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
            dateRangeCalendarManager.value?.resetSelectedDateRange()
            adapterEventCalendarMonths.value?.resetAllSelectedViews()
            adapterEventCalendarMonths.value?.invalidateCalendar()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(530.dp)
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
            .clickableNoEffect { }
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "날짜 선택",
                color = getColor().black,
                style = largeTitle02
            )

            Spacer(Modifier.weight(1f))

            CloseButton {
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Closed
                    )
                    isDimBackgroundShowing.value = false
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        AndroidView(
            factory = { context ->
                DateRangeCalendarView(context).apply {
                    dateRangeCalendarManager.value = getCalendarManager()
                    adapterEventCalendarMonths.value = getAdapterEventCalendarMonths()
                    this.setCalendarListener(object : CalendarListener {
                        override fun onFirstDateSelected(startDate: Calendar) {}
                        override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {}
                    })
                }
            }
        )

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(48.dp)
        ) {
            ResetButton {
                dateRangeCalendarManager.value?.resetSelectedDateRange()
                adapterEventCalendarMonths.value?.resetAllSelectedViews()
                adapterEventCalendarMonths.value?.invalidateCalendar()
            }

            Spacer(Modifier.weight(1f))

            ApplyButton {
                if (dateRangeCalendarManager.value?.mMinSelectedDate != null && dateRangeCalendarManager.value?.mMaxSelectedDate != null) {
                    updateStartCalendar(dateRangeCalendarManager.value?.mMinSelectedDate)
                    updateEndCalendar(dateRangeCalendarManager.value?.mMaxSelectedDate)
                    Log.e("start", "${dateRangeCalendarManager.value?.mMinSelectedDate?.get(Calendar.DATE)}")
                    Log.e("end", "${dateRangeCalendarManager.value?.mMaxSelectedDate?.get(Calendar.DATE)}")
                    coroutineScope.launch {
                        anchoredDraggableState.animateTo(
                            AnchoredDraggableContentState.Closed
                        )
                        isDimBackgroundShowing.value = false
                    }
                } else {
                    Toast.makeText(context, "올바른 날짜 구간을 선택해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        Spacer(Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LocationSelectionContent(
    locationList: List<String>,
    isDimBackgroundShowing: MutableState<Boolean>,
    anchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    selectedLocationList: SnapshotStateList<Boolean>,
    tmpSelectedLocationList: SnapshotStateList<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current.density
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
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
            Text(
                text = "지역",
                color = getColor().black,
                style = largeTitle02
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "(${tmpSelectedLocationList.count { it }} / 15)",
                color = Color(0xFF717171),
                style = body02
            )

            Spacer(Modifier.weight(1f))

            CloseButton {
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Closed
                    )
                    isDimBackgroundShowing.value = false
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            contentPadding = PaddingValues(8.dp),
            columns = GridCells.Fixed(4)
        ) {
            itemsIndexed(locationList) { idx, item ->
                Box(
                    modifier = Modifier
                        .width(86.dp)
                        .height(56.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(40.dp)
                            .border(
                                border = BorderStroke(
                                    color = if (tmpSelectedLocationList[idx]) getColor().main50 else getColor().gray02,
                                    width = 1.dp
                                ),
                                shape = RoundedCornerShape(50)
                            )
                            .background(
                                color = if (tmpSelectedLocationList[idx]) getColor().main10 else Color.Transparent,
                                shape = RoundedCornerShape(50)
                            )
                            .clickableNoEffect {
                                tmpSelectedLocationList[idx] = !tmpSelectedLocationList[idx]
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            color = getColor().gray01,
                            style = body01
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(48.dp)
        ) {
            ResetButton {
                tmpSelectedLocationList.forEachIndexed { idx, _ ->
                    tmpSelectedLocationList[idx] = false
                }
            }

            Spacer(Modifier.weight(1f))

            ApplyButton {
                tmpSelectedLocationList.forEachIndexed { idx, _ ->
                    selectedLocationList[idx] = tmpSelectedLocationList[idx]
                }
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Closed
                    )
                    isDimBackgroundShowing.value = false
                }
            }
        }

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun CloseButton(
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(32.dp)
            .clickableNoEffect {
                onClick()
            },
        painter = painterResource(R.drawable.ic_close),
        contentDescription = null
    )
}

@Composable
private fun ResetButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .clickableNoEffect {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_reset),
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "초기화",
            color = getColor().black,
            style = body01
        )
    }
}

@Composable
private fun ApplyButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(
                color = getColor().main,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "적용하기",
            color = getColor().white,
            style = bodyBold
        )
    }
}

// anchoredDraggableState의 offset은 pixel단위로 측정됨.
// y offset이 0일 때 statusbar 바로 아래부터 시작됨.
@OptIn(ExperimentalFoundationApi::class)
private val dateAnchoredDraggableState = AnchoredDraggableState(
    initialValue = AnchoredDraggableContentState.Closed,
    positionalThreshold = { it: Float -> it * 0.5f },
    velocityThreshold = { 100f },
    animationSpec = tween(500)
).apply {
    updateAnchors(
        DraggableAnchors {
            AnchoredDraggableContentState.Open at TOTAL_SCREEN_HEIGHT - 530f - SYSTEM_STATUS_BAR_HEIGHT - SYSTEM_NAVIGATION_BAR_HEIGHT
            AnchoredDraggableContentState.Closed at TOTAL_SCREEN_HEIGHT
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
private val locationAnchoredDraggableState = AnchoredDraggableState(
    initialValue = AnchoredDraggableContentState.Closed,
    positionalThreshold = { it: Float -> it * 0.5f },
    velocityThreshold = { 100f },
    animationSpec = tween(500)
).apply {
    updateAnchors(
        DraggableAnchors {
            AnchoredDraggableContentState.Open at TOTAL_SCREEN_HEIGHT - 400f - SYSTEM_STATUS_BAR_HEIGHT - SYSTEM_NAVIGATION_BAR_HEIGHT
            AnchoredDraggableContentState.Closed at TOTAL_SCREEN_HEIGHT - SYSTEM_STATUS_BAR_HEIGHT
        }
    )
}


@CustomPreview
@Composable
private fun FestivalListScreenPreview() {
    NanaLandTheme {
    }
}
package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.animation.core.exponentialDecay
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.jeju.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.listscreen.filter.parts.DateFilterDialogResetButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogApplyButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilterDialogCloseButton
import com.jeju.nanaland.ui.component.listscreen.filter.parts.date.DateFilterDialogTitle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.daterangecalendar.customviews.AdapterEventCalendarMonths
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarDateRangeManagerImpl
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarListener
import com.jeju.nanaland.util.daterangecalendar.customviews.DateRangeCalendarView
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DateFilterBottomDialog(
    anchoredDraggableState: AnchoredDraggableState<AnchoredDraggableContentState>,
    hideDimBackground: () -> Unit,
    startCalendar: Calendar?,
    endCalendar: Calendar?,
    updateStartCalendar: (Calendar) -> Unit,
    updateEndCalendar: (Calendar) -> Unit,
    updateList: () -> Unit,
    clearList: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val dateRangeCalendarManager = remember { mutableStateOf<CalendarDateRangeManagerImpl?>(null) }
    val adapterEventCalendarMonths = remember { mutableStateOf<AdapterEventCalendarMonths?>(null) }
    LaunchedEffect(anchoredDraggableState.targetValue) {
        if (anchoredDraggableState.targetValue == AnchoredDraggableContentState.Open) {
            dateRangeCalendarManager.value?.mMinSelectedDate = startCalendar
            dateRangeCalendarManager.value?.mMaxSelectedDate = endCalendar
            adapterEventCalendarMonths.value?.invalidateCalendar()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(540.dp)
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
            .clickableNoEffect {}
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            DateFilterDialogTitle()

            Spacer(Modifier.weight(1f))

            FilterDialogCloseButton {
                coroutineScope.launch {
                    anchoredDraggableState.animateTo(AnchoredDraggableContentState.Closed)
                    hideDimBackground()
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        AndroidView(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, end = 12.dp),
            factory = {
                DateRangeCalendarView(context).apply {
                    dateRangeCalendarManager.value = getCalendarManager()
                    adapterEventCalendarMonths.value = getAdapterEventCalendarMonths()
                    val currentCalendar = Calendar.getInstance()
                    val startRangeCalendar = Calendar.getInstance()
                    val endRangeCalendar = Calendar.getInstance()
                    startRangeCalendar.set(currentCalendar.get(Calendar.YEAR) - 2, currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DATE))
                    endRangeCalendar.set(currentCalendar.get(Calendar.YEAR) + 2, currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DATE))
                    dateRangeCalendarManager.value?.setSelectableDateRange(startRangeCalendar, endRangeCalendar)
                    this.setCalendarListener(object : CalendarListener {
                        override fun onFirstDateSelected(startDate: Calendar) {
                            dateRangeCalendarManager.value?.mMinSelectedDate = startDate
                            dateRangeCalendarManager.value?.mMaxSelectedDate = null
                        }
                        override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                            dateRangeCalendarManager.value?.mMinSelectedDate = startDate
                            dateRangeCalendarManager.value?.mMaxSelectedDate = endDate
                        }
                    })
                }
            }
        )

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(48.dp)
        ) {
            DateFilterDialogResetButton {
                dateRangeCalendarManager.value?.mMinSelectedDate = Calendar.getInstance()
                dateRangeCalendarManager.value?.mMaxSelectedDate = Calendar.getInstance()
//                dateRangeCalendarManager.value?.resetSelectedDateRange()
//                adapterEventCalendarMonths.value?.resetAllSelectedViews()
                adapterEventCalendarMonths.value?.invalidateCalendar()
            }

            Spacer(Modifier.width(16.dp))

            FilterDialogApplyButton {
                if (dateRangeCalendarManager.value?.mMinSelectedDate != null && dateRangeCalendarManager.value?.mMaxSelectedDate == null) {
                    updateStartCalendar(dateRangeCalendarManager.value?.mMinSelectedDate ?: Calendar.getInstance())
                    updateEndCalendar(dateRangeCalendarManager.value?.mMinSelectedDate ?: Calendar.getInstance())
                } else {
                    updateStartCalendar(dateRangeCalendarManager.value?.mMinSelectedDate ?: Calendar.getInstance())
                    updateEndCalendar(dateRangeCalendarManager.value?.mMaxSelectedDate ?: Calendar.getInstance())
                }
                clearList()
                updateList()
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
fun getDateAnchoredDraggableState() = AnchoredDraggableState(
    initialValue = AnchoredDraggableContentState.Closed,
    positionalThreshold = { it: Float -> it * 0.5f },
    velocityThreshold = { 100f },
    snapAnimationSpec = tween(400),
    decayAnimationSpec = exponentialDecay(400f),
).apply {
    updateAnchors(
        DraggableAnchors {
            AnchoredDraggableContentState.Open at TOTAL_SCREEN_HEIGHT - 530f - SYSTEM_STATUS_BAR_HEIGHT - SYSTEM_NAVIGATION_BAR_HEIGHT
            AnchoredDraggableContentState.Closed at TOTAL_SCREEN_HEIGHT
        }
    )
}
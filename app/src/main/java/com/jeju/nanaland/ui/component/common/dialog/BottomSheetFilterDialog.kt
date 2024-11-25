package com.jeju.nanaland.ui.component.common.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarListener
import com.jeju.nanaland.util.daterangecalendar.customviews.DateRangeCalendarView
import com.jeju.nanaland.util.resource.getDrawable
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.resource.getStringArray
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch
import java.util.Calendar

enum class BottomSheetFilterDialogType{
    Location,
    Activity,
    Art,
    Restaurant,
}
/** 지역, 키워드 선택 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetFilterDialog(
    type: BottomSheetFilterDialogType,
    onDismiss: () -> Unit,
    stringList: List<String>,
    selectedList: SnapshotStateList<Boolean>,
    updateList: () -> Unit,
    clearList: () -> Unit
) {
    val tmpSelectedList = remember(selectedList) {
        selectedList.toMutableStateList()
    }
    val title = remember(type) {
        getString(when(type){
            BottomSheetFilterDialogType.Location -> R.string.location_filter_dialog_지역
            BottomSheetFilterDialogType.Activity -> R.string.common_액티비티
            BottomSheetFilterDialogType.Art -> R.string.common_문화예술
            BottomSheetFilterDialogType.Restaurant -> R.string.common_종류
        })
    }
    BottomSheetFilterDialogImpl(
        onDismiss = onDismiss,
        title = title,
        count = "(${tmpSelectedList.count { it }} / ${tmpSelectedList.size})",
        onApply = {
            val differentCount = selectedList.zip(tmpSelectedList).count { (x, y) -> x != y }
            if (differentCount != 0) {
                tmpSelectedList.forEachIndexed { idx, item ->
                    selectedList[idx] = item
                }
                clearList()
                updateList()
            }
        }
    ) {
        Spacer(Modifier.height(16.dp))

        if(type == BottomSheetFilterDialogType.Location) {
            Box {
                MapImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 21.dp),
                    selectedLocationList = tmpSelectedList
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.BottomEnd),
                    text = getString(R.string.festival_list_screen_image_caption),
                    color = getColor().gray01,
                    textAlign = TextAlign.End,
                    style = caption01
                )
            }

            Spacer(Modifier.height(16.dp))
        }

        SelectedBoxList(
            columnCount = if(type == BottomSheetFilterDialogType.Location) 4 else 3,
            stringList = stringList,
            selectList = tmpSelectedList,
            onSelect = { idx ->
                tmpSelectedList[idx] = !tmpSelectedList[idx]
            }
        )

        Spacer(Modifier.height(16.dp))

        AllAndClear(
            onAll = { tmpSelectedList.forEachIndexed { idx, _ ->
                    tmpSelectedList[idx] = true
            }},
            onClear = { tmpSelectedList.forEachIndexed { idx, _ ->
                    tmpSelectedList[idx] = false
            }}
        )

        Spacer(Modifier.height(16.dp))
    }
}
/** 날짜 선택 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetFilterDialog(
    onDismiss: () -> Unit,
    startCalendar: Calendar?,
    endCalendar: Calendar?,
    updateStartCalendar: (Calendar) -> Unit,
    updateEndCalendar: (Calendar) -> Unit,
    updateList: () -> Unit,
    clearList: () -> Unit,
) {
    val context = LocalContext.current
    val calendarView = remember {
        DateRangeCalendarView(context).apply {
            val currentCalendar = Calendar.getInstance()
            val startRangeCalendar = Calendar.getInstance()
            val endRangeCalendar = Calendar.getInstance()
            startRangeCalendar.set(currentCalendar.get(Calendar.YEAR) - 2, currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DATE))
            endRangeCalendar.set(currentCalendar.get(Calendar.YEAR) + 2, currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DATE))
            getCalendarManager().setSelectableDateRange(startRangeCalendar, endRangeCalendar)

            setCalendarListener(object : CalendarListener {
                override fun onFirstDateSelected(startDate: Calendar) {
                    getCalendarManager().mMinSelectedDate = startDate
                    getCalendarManager().mMaxSelectedDate = null
                }
                override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                    getCalendarManager().mMinSelectedDate = startDate
                    getCalendarManager().mMaxSelectedDate = endDate
                }
            })
        }
    }

    LaunchedEffect(Unit) {
        calendarView.getCalendarManager().mMinSelectedDate = startCalendar
        calendarView.getCalendarManager().mMaxSelectedDate = endCalendar
        calendarView.setCurrentMonth(endCalendar ?: Calendar.getInstance())
        calendarView.getAdapterEventCalendarMonths().invalidateCalendar()
    }

    BottomSheetFilterDialogImpl(
        onDismiss = onDismiss,
        title = getString(R.string.date_filter_dialog_날짜_선택),
        onApply = {
            if (calendarView.getCalendarManager().mMinSelectedDate != null && calendarView.getCalendarManager().mMaxSelectedDate == null) {
                updateStartCalendar(calendarView.getCalendarManager().mMinSelectedDate ?: Calendar.getInstance())
                updateEndCalendar(calendarView.getCalendarManager().mMinSelectedDate ?: Calendar.getInstance())
            } else {
                updateStartCalendar(calendarView.getCalendarManager().mMinSelectedDate ?: Calendar.getInstance())
                updateEndCalendar(calendarView.getCalendarManager().mMaxSelectedDate ?: Calendar.getInstance())
            }
            clearList()
            updateList()
        }
    ) {

        AndroidView(
            modifier = Modifier.height(330.dp),
            factory = { calendarView }
        )

        Spacer(Modifier.height(16.dp))

        AllAndClear(
            allString = getString(R.string.date_filter_dialog_all_select),
            onAll = {
                calendarView.getCurrentMonth()?.let {
                    val first = it
                    val last = Calendar.getInstance().apply {
                        time = it.time
                        set(Calendar.DATE, getActualMaximum(Calendar.DAY_OF_MONTH))
                    }
                    calendarView.getCalendarManager().setSelectedDateRange(first, last)
                }
                calendarView.getAdapterEventCalendarMonths().invalidateCalendar()
            },
            clearString = getString(R.string.date_filter_dialog_초기화),
            onClear = {
                val current = Calendar.getInstance()
                calendarView.getCalendarManager().mMinSelectedDate = current
                calendarView.getCalendarManager().mMaxSelectedDate = current
                calendarView.setCurrentMonth(current)

                calendarView.getAdapterEventCalendarMonths().invalidateCalendar()
            }
        )

        Spacer(Modifier.height(16.dp))
    }
}
/** 계절 선택 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetFilterDialog(
    onDismiss: () -> Unit,
    selectedSeasonList: SnapshotStateList<Boolean>,
    updateList: () -> Unit,
    clearList: () -> Unit,
) {
    var isShow by remember { mutableStateOf(true) }

    BottomSheetFilterDialogImpl(
        onDismiss = onDismiss,
        isShow = isShow,
        title = getString(R.string.date_filter_dialog_날짜_선택),
        onApply = null
    ) {
        Spacer(Modifier.height(19.dp))

        repeat(4) { seasonIdx ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (selectedSeasonList[seasonIdx]) getColor().main10 else getColor().transparent
                    )
                    .clickableNoEffect {
                        if (!selectedSeasonList[seasonIdx]) {
                            repeat(4) { selectedSeasonList[it] = false }
                            selectedSeasonList[seasonIdx] = true
                            clearList()
                            updateList()
                        }
                        isShow = false
                    }
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = getStringArray(R.array.season)[seasonIdx],
                    color = if (selectedSeasonList[seasonIdx]) getColor().main else getColor().black,
                    style = if (selectedSeasonList[seasonIdx]) body02Bold else body02
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = getStringArray(R.array.season_desc)[seasonIdx],
                    color = getColor().gray01,
                    style = body02
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetFilterDialogImpl(
    onDismiss: () -> Unit,
    isShow:Boolean = true,
    title: String,
    count: String = "",
    onApply: (() -> Unit)?,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(true)
    if(!isShow){
        LaunchedEffect(Unit) {
            state.hide()
            if (!state.isVisible)
                onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch { state.hide() }.invokeOnCompletion {
                if (!state.isVisible)
                    onDismiss()
            }
        },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        containerColor = getColor().white,
        dragHandle = null,
    ) {
        NanaLandTheme {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = getColor().white,
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
                    .padding(vertical = 24.dp)
            ) {

                /** 상단 - 타이틀 및 닫기 버튼 **/
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = title,
                        color = getColor().black,
                        style = bodyBold
                    )

                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = count,
                        color = getColor().gray01,
                        style = caption01
                    )

                    Spacer(Modifier.weight(1f))

                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .clickableNoEffect {
                                scope
                                    .launch { state.hide() }
                                    .invokeOnCompletion {
                                        if (!state.isVisible)
                                            onDismiss()
                                    }
                            },
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null
                    )
                }

                content()


                /** 적용하기 **/
                if(onApply != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .background(
                                color = getColor().main,
                                shape = RoundedCornerShape(50)
                            )
                            .clickableNoEffect {
                                onApply()
                                scope
                                    .launch { state.hide() }
                                    .invokeOnCompletion {
                                        if (!state.isVisible)
                                            onDismiss()
                                    }
                            }
                            .padding(vertical = 11.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = getString(R.string.filter_dialog_common_적용하기),
                            color = getColor().white,
                            style = bodyBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectedBoxList(
    columnCount: Int,
    stringList: List<String>,
    selectList: List<Boolean>,
    onSelect: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            verticalArrangement = Arrangement.spacedBy(
                if(columnCount == 3) 12.dp else 10.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(
                if(columnCount == 3) 21.dp else 16.dp
            ),
        ) {
            itemsIndexed(stringList) { idx, item ->
                Text(
                    text = item,
                    color = if (selectList[idx]) getColor().main else getColor().gray01,
                    style = body02,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(
                            border = BorderStroke(
                                color = if (selectList[idx]) getColor().main50 else getColor().gray02,
                                width = 1.dp
                            ),
                            shape = RoundedCornerShape(50)
                        )
                        .background(
                            color = if (selectList[idx]) getColor().main10 else Color.Transparent,
                            shape = RoundedCornerShape(50)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 7.dp)
                        .clickableNoEffect { onSelect(idx) }
                )
            }
        }
    }
}

@Composable
private fun AllAndClear(
    allString: String = getString(R.string.location_filter_dialog_전체선택),
    onAll: () -> Unit,
    clearString: String = getString(R.string.location_filter_dialog_해제),
    onClear: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        /** 전체 선택**/
        Row(
            modifier = Modifier
                .clickableNoEffect(onAll)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = allString,
                color = getColor().black,
                style = body02
            )
        }
        Spacer(Modifier.weight(1f))
        /** 전체 해제**/
        Row(
            modifier = Modifier
                .clickableNoEffect(onClear)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_reset),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = clearString,
                color = getColor().black,
                style = body02
            )
        }
    }
}



@Composable
private fun MapImage(
    modifier: Modifier = Modifier,
    selectedLocationList: List<Boolean>
) {
    Box(modifier) {
        Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_unselected)), contentDescription = null)

        if (selectedLocationList[3])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_1)), contentDescription = null)
        if (selectedLocationList[5])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_2)), contentDescription = null)
        if (selectedLocationList[1])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_3)), contentDescription = null)
        if (selectedLocationList[0])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_4)), contentDescription = null)
        if (selectedLocationList[2])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_5)), contentDescription = null)
        if (selectedLocationList[4])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_6)), contentDescription = null)
        if (selectedLocationList[9])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_7)), contentDescription = null)
        if (selectedLocationList[10])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_8)), contentDescription = null)
        if (selectedLocationList[8])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_9)), contentDescription = null)
        if (selectedLocationList[11])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_10)), contentDescription = null)
        if (selectedLocationList[12])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_11)), contentDescription = null)
        if (selectedLocationList[13])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_12)), contentDescription = null)
        if (selectedLocationList[7])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_13)), contentDescription = null)
        if (selectedLocationList[6])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_14)), contentDescription = null)
    }
}

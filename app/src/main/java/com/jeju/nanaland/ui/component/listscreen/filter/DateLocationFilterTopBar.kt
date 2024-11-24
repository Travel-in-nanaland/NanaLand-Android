package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.listscreen.filter.parts.date.DateFilterBox
import com.jeju.nanaland.ui.component.listscreen.filter.parts.location.LocationFilterBox
import java.util.Calendar

@Composable
fun DateLocationFilterTopBar(
    selectedLocationList: SnapshotStateList<Boolean>,
    locationList: List<String>,
    openDateFilterDialog: () -> Unit,
    openLocationFilterDialog: () -> Unit,
    startCalendar: Calendar,
    endCalendar: Calendar,
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(Modifier.weight(1f))

        DateFilterBox(
            openDateFilterDialog = openDateFilterDialog,
            startCalendar = startCalendar,
            endCalendar = endCalendar
        )

        Spacer(Modifier.width(8.dp))

        LocationFilterBox(
            locationList = locationList,
            openLocationFilterDialog = openLocationFilterDialog,
            selectedLocationList = selectedLocationList
        )
    }
}
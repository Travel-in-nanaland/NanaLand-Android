package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.listscreen.filter.parts.LocationFilterBox

@Composable
fun LocationFilterTopBar(
    selectedLocationList: SnapshotStateList<Boolean>,
    locationList: List<String>,
    openLocationFilterDialog: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        LocationFilterBox(
            modifier = Modifier.weight(1f, false),
            locationList = locationList,
            openLocationFilterDialog = openLocationFilterDialog,
            selectedLocationList = selectedLocationList
        )
    }
}
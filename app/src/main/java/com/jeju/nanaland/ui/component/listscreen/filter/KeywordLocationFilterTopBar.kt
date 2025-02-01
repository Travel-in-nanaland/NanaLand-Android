package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.foundation.layout.Arrangement
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
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.listscreen.filter.parts.KeywordFilterBox
import com.jeju.nanaland.ui.component.listscreen.filter.parts.LocationFilterBox
import com.jeju.nanaland.util.resource.getString

@Composable
fun KeywordLocationFilterTopBar(
    text: String = getString(R.string.common_종류),
    selectedKeywordList: SnapshotStateList<Boolean>,
    keywordList: List<String>,
    selectedLocationList: SnapshotStateList<Boolean>,
    locationList: List<String>,
    openKeywordFilterDialog: () -> Unit,
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
        KeywordFilterBox(
            modifier = Modifier.weight(1f, false),
            text = text,
            keywordList = keywordList,
            openKeywordFilterDialog = openKeywordFilterDialog,
            selectedKeywordList = selectedKeywordList
        )

        Spacer(Modifier.width(8.dp))

        LocationFilterBox(
            modifier = Modifier.weight(1f, false),
            locationList = locationList,
            openLocationFilterDialog = openLocationFilterDialog,
            selectedLocationList = selectedLocationList
        )
    }
}
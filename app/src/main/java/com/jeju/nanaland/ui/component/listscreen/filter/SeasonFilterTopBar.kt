package com.jeju.nanaland.ui.component.listscreen.filter

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.listscreen.filter.parts.FilteredItemCount
import com.jeju.nanaland.ui.component.listscreen.filter.parts.season.SeasonFilterBox
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SeasonFilterTopBar(
    count: UiState<Int>,
    selectedSeasonList: SnapshotStateList<Boolean>,
    openSeasonFilterDialog: () -> Unit,
    showDimBackground: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (count) {
            is UiState.Loading -> {}
            is UiState.Success -> { FilteredItemCount(count = count.data) }
            is UiState.Failure -> {}
        }

        Spacer(Modifier.weight(1f))

        SeasonFilterBox(
            showDimBackground = showDimBackground,
            openSeasonFilterDialog = openSeasonFilterDialog,
            selectedSeasonList = selectedSeasonList
        )
    }
}
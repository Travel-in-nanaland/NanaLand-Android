package com.jeju.nanaland.ui.component.listscreen.filter.parts.season

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SeasonFilterDialogTitle() {
    Text(
        text = getString(R.string.season_filter_dialog_계절_선택),
        color = getColor().black,
        style = title02Bold
    )
}

@ComponentPreview
@Composable
private fun SeasonFilterDialogTitlePreview() {
    NanaLandTheme {
        SeasonFilterDialogTitle()
    }
}
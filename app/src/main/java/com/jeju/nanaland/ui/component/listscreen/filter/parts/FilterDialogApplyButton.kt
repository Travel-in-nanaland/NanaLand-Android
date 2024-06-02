package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun FilterDialogApplyButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = getColor().main,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getString(R.string.filter_dialog_common_적용하기),
            color = getColor().white,
            style = bodyBold
        )
    }
}
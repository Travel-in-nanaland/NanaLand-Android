package com.jeju.nanaland.ui.component.detailscreen.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ColumnScope.DetailScreenInformationModificationProposalButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .wrapContentSize()
            .height(40.dp)
            .background(
                color = getColor().gray02,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect { onClick() }
            .padding(start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getString(R.string.info_modification_proposal_정보_수정_제안),
            color = getColor().gray01,
            style = body02Bold,
            textAlign = TextAlign.Center
        )
    }
}
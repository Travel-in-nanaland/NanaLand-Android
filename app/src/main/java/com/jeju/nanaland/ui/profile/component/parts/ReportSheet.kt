package com.jeju.nanaland.ui.profile.component.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportSheet(
    onDismissRequest: () -> Unit,
    onReport: () -> Unit
) {
    ModalBottomSheet(
        containerColor = getColor().white,
        shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
        onDismissRequest = onDismissRequest,
        dragHandle = null
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .size(28.dp)
                    .align(Alignment.End)
                    .clickableNoEffect { onDismissRequest() },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .clickableNoEffect { onReport() }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                text = getString(R.string.common_신고),
                style = body01,
                color = getColor().black
            )
        }
    }
}
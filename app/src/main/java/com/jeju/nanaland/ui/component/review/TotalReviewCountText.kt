package com.jeju.nanaland.ui.component.review

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun TotalReviewCountText(count: Int) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = getString(R.string.common_후기),
            color = getColor().black,
            style = bodyBold
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = count.toString(),
            color = getColor().main,
            style = body01
        )
    }
}
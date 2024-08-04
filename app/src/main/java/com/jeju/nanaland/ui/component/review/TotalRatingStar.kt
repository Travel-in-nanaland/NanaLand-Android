package com.jeju.nanaland.ui.component.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun TotalRatingStar(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(if (rating - 1 >= it) R.drawable.ic_star else R.drawable.ic_star_empty),
                contentDescription = null
            )
        }

        Spacer(Modifier.width(8.dp))

        Text(
            text = String.format("%.1f", rating),
            color = getColor().black,
            style = body02Bold
        )
    }
}
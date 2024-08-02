package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ReviewRatingText(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(R.drawable.ic_star_rating),
            contentDescription = null
        )
        Text(
            text = String.format("%.2f", rating),
            color = getColor().black,
            style = caption01
        )
    }
}
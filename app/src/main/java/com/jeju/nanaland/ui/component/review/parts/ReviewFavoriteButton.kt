package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun RowScope.ReviewFavoriteButton(
    isFavorite: Boolean,
    favoriteCount: Int
) {
    Row(
        modifier = Modifier
            .align(Alignment.Top)
            .height(28.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = getColor().gray02
                ),
                shape = RoundedCornerShape(50)
            )
            .padding(start = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined),
            contentDescription = null
        )

        Spacer(Modifier.width(2.dp))

        Text(
            text = favoriteCount.toString(),
            color = getColor().black,
            style = caption01
        )
    }
}
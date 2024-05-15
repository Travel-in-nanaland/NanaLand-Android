package com.jeju.nanaland.ui.component.infomodification

import android.view.RoundedCorner
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun InfoModificationProposalScreenBox(
    @DrawableRes drawableId: Int,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = getColor().gray02
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickableNoEffect { onClick },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(12.dp))

        Image(
            modifier = Modifier.size(28.dp),
            painter = painterResource(drawableId),
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            color = getColor().black,
            style = body02
        )

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null
        )

        Spacer(Modifier.width(12.dp))
    }
}
package com.jeju.nanaland.ui.component.nonmember.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodySemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun NonMemberDialogBottomButton(onClick: () -> Unit) {
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
        Row {
            Text(
                text = getString(R.string.non_member_dialog_go_sign_up),
                color = getColor().white,
                style = bodySemiBold
            )

            Spacer(Modifier.width(4.dp))

            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().white)
            )
        }
    }
}
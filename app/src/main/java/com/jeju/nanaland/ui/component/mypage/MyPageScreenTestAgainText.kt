package com.jeju.nanaland.ui.component.mypage

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
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun MyPageScreenTestAgainContent(onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickableNoEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "테스트 다시하기",
            color = getColor().black,
            style = caption01SemiBold
        )

        Spacer(Modifier.width(4.dp))

        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null
        )
    }
}
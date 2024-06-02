package com.jeju.nanaland.ui.component.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ColumnScope.MyPageScreenNickname(onClick: () -> Unit,) {
    if (UserData.provider == "GUEST") {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickableNoEffect { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "로그인이 필요해요",
                color = getColor().black,
                style = largeTitle02
            )

            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null
            )
        }
    } else {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = UserData.nickname,
            color = getColor().black,
            style = largeTitle02
        )
    }
}
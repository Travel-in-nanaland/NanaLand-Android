package com.jeju.nanaland.ui.component.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.mypage.parts.MyPageScreenLevel
import com.jeju.nanaland.ui.component.mypage.parts.MyPageScreenProfileImage
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ColumnScope.MyPageScreenProfileImageWithLevel(
    imageUri: String,
    level: Long
) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(108.dp)
    ) {
        if (UserData.provider == "GUEST") {
            Box(
                Modifier
                    .size(100.dp)
                    .background(
                        color = getColor().main,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.width(40.dp),
                    painter = painterResource(R.drawable.ic_mandarine_filled_white),
                    contentDescription = null
                )
            }
        } else {
            MyPageScreenProfileImage(imageUri = imageUri)

            MyPageScreenLevel(level = level)
        }
    }
}
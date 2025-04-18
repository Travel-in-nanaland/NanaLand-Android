package com.jeju.nanaland.ui.profile.component.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.notice.NoticeSummery
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.string.toFormatString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawColoredShadow

@Composable
fun ProfileNoticeRow(
    data: NoticeSummery,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoEffect { onClick(data.id) }
            .drawColoredShadow(
                color = Color.Black,
                shadowRadius = 8.dp,
                heightControl = (-5).dp,
                widthControl = (-5).dp,
            )
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = getColor().white,
            )
            .padding(12.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(getColor().main)
                .padding(4.dp),
            painter = painterResource(
                when(data.noticeCategory){
                    "공지 사항" -> R.drawable.ic_alarm_notice // TODO 확인 필요
                    else -> R.drawable.ic_alarm_fix
                }
            ),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = data.noticeCategory, // TODO 확인 필요
                color = getColor().main,
                style = caption01SemiBold
            )
            Text(
                text = data.title,
                color = getColor().black,
                style = body02Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = data.createdAt.toFormatString(),
                color = getColor().gray01,
                style = caption02
            )
        }
        Icon(
            modifier = Modifier.align(Alignment.Bottom),
            painter = painterResource(id = R.drawable.ic_arrow_right_half),
            contentDescription = null,
            tint = getColor().black
        )
    }
}
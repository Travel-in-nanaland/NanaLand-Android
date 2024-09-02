package com.jeju.nanaland.ui.report.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.report.ClaimType
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun ReportCategoryScreen(
    onClick: (ClaimType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = getString(R.string.report_category_title),
            style = title01Bold,
            color = getColor().black
        )
        Text(
            text = getString(R.string.report_category_subtitle),
            style = body02,
            color = getColor().gray01
        )
        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ClaimType.entries.forEach {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = getColor().gray02,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onClick(it) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringArrayResource(R.array.report_reason)[it.stringIndex],
                        style = body02,
                        color = getColor().black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = getColor().black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}
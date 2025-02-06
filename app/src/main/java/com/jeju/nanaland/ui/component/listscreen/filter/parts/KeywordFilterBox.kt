package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun KeywordFilterBox(
    modifier: Modifier,
    text: String,
    keywordList: List<String>,
    openKeywordFilterDialog: () -> Unit,
    selectedKeywordList: SnapshotStateList<Boolean>
) {
    val borderColor = getColor().gray02
    Row(
        modifier = modifier
            .height(32.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect(openKeywordFilterDialog),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.width(12.dp))

        Text(
            modifier = Modifier.weight(1f, false),
            text = (when (selectedKeywordList.count { it }) {
                0 -> text
                1 -> keywordList[selectedKeywordList.indexOfFirst { it }]
                else -> getString(R.string.common_외, keywordList[selectedKeywordList.indexOfFirst { it }], selectedKeywordList.count { it } - 1)
            }).replace('\n',' '),
            color = getColor().gray01,
            style = caption01,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.width(4.dp))

        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.ic_arrow_down),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(12.dp))
    }
}
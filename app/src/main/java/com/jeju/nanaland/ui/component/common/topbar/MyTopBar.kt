package com.jeju.nanaland.ui.component.common.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.shadowTopNav
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.conditional

@Composable
fun MyTopBar(
    title: String = "",
    showShadow: Boolean = true,
    onBackButtonClicked: (() -> Unit)? = null,
    vararg menus: Pair<Int, () -> Unit>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT.dp)
            .conditional(showShadow) {
                shadowTopNav()
            }
            .background(getColor().white)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        onBackButtonClicked?.let {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickableNoEffect(it)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
            )
        }

        menus.forEachIndexed { i, _ ->
            if(onBackButtonClicked == null || i != 0)
                Spacer(modifier = Modifier.size(32.dp))
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = title,
            color = getColor().black,
            style = title01Bold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        menus.forEach {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickableNoEffect(it.second),
                painter = painterResource(id = it.first),
                contentDescription = null,
            )
        }
        if(menus.isEmpty() && onBackButtonClicked != null)
            Spacer(modifier = Modifier.size(32.dp))

    }
}

@Preview(showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewMyTopBars(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MyTopBar(
            title = "가나다라",
            showShadow = false
        )
        MyTopBar(
            title = "가나다라",
            onBackButtonClicked = {}
        )
        MyTopBar(
            title = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
            onBackButtonClicked = {},
            menus = arrayOf(
                R.drawable.ic_heart_outlined to {},
                R.drawable.ic_share to {}
            )
        )
        MyTopBar(
            title = "가나다라",
            onBackButtonClicked = {},
            menus = arrayOf(
                R.drawable.ic_heart_outlined to {},
                R.drawable.ic_share to {},
                R.drawable.ic_heart_outlined to {},
            )
        )

    }
}
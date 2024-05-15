package com.jeju.nanaland.ui.component.main.searchresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SearchResultScreenPreparingServiceContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(R.drawable.ic_smilingface_outlined),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "해당 서비스 준비 중입니다.\n다음에 만나요!",
            color = getColor().gray01,
            textAlign = TextAlign.Center,
            style = body02
        )
    }
}
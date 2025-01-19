package com.jeju.nanaland.ui.component.main.searchresult.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun SearchResultScreenEmptySearchResultContent(isImageSmall: Boolean = false) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size((if (isImageSmall) 48 else 100).dp),
            painter = painterResource(id = R.drawable.img_info_glasses),
            contentDescription = null
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = getString(R.string.search_result_screen_no_result),
            color = getColor().gray01,
            style = body01,
            textAlign = TextAlign.Center
        )
    }
}
package com.nanaland.ui.component.detailscreen.parts.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02Bold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenNoticeTitle(text: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_warning_outlined),
            contentDescription = null
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = text ?: "",
            color = getColor().main,
            style = body02Bold
        )
    }
}

@ComponentPreview
@Composable
private fun DetailScreenNoticeTitlePreview() {
    NanaLandTheme {
        DetailScreenNoticeTitle(text = "Title")
    }
}
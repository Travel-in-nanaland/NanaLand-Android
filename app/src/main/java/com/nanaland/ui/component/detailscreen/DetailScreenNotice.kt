package com.nanaland.ui.component.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.detailscreen.parts.notice.DetailScreenNoticeContent
import com.nanaland.ui.component.detailscreen.parts.notice.DetailScreenNoticeTitle
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.ScreenPreview

@Composable
fun DetailScreenNotice(
    title: String?,
    content: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = getColor().main10,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        DetailScreenNoticeTitle(text = title)

        Spacer(Modifier.height(4.dp))

        DetailScreenNoticeContent(text = content)
    }
}

@ScreenPreview
@Composable
private fun DetailScreenNoticePreview() {
    NanaLandTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            DetailScreenNotice(
                title = "title",
                content = "content"
            )
        }
    }
}
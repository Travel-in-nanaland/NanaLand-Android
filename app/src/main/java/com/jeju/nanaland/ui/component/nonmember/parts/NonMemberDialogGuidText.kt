package com.jeju.nanaland.ui.component.nonmember.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.type.getCategoryType

@Composable
fun NonMemberDialogGuidText() {
    Text(
        text = """
            나나랜드의 회원이 되셔야,
            모든 서비스를
            이용하실 수 있습니다✨
        """.trimIndent(),
        color = getColor().black,
        style = title01Bold,
        textAlign = TextAlign.Center
    )
}
package com.jeju.nanaland.ui.main.jejustory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomTopBarNoBackButton
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun JejuStoryScreen() {
    JejuStoryScreen(
        isContent = true
    )
}

@Composable
private fun JejuStoryScreen(
    isContent: Boolean
) {
    Column(Modifier.fillMaxSize()) {
        CustomTopBarNoBackButton(title = "제주 이야기")

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(R.drawable.img_airplane),
                    contentDescription = null
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = """
                        해당 서비스는 준비 중입니다.
                        다음에 만나요!
                    """.trimIndent(),
                    color = getColor().gray01,
                    style = body01,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun JejuStoryScreenPreview() {

}
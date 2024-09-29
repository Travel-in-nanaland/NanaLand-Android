package com.jeju.nanaland.ui.component.signup.typetestcompletion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.ui.theme.largeTitle02Regular
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestCompletionScreenText() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        listOf(
            R.string.type_test_screen_text_page1_text1,
            R.string.type_test_screen_text_page1_text2,
            R.string.type_test_screen_text_page1_text3,
        ).forEach { stringRes ->
            runCatching {
                Text(
                    text = getString(stringRes),
                    style = largeTitle02Regular,
                    color = getColor().black,
                    textAlign = TextAlign.Center
                )
            }.getOrElse {
                TextWithPointColor(
                    text = getString(stringRes, UserData.nickname),
                    style = largeTitle02.copy(textAlign = TextAlign.Center),
                    color = getColor().black,
                )
            }
        }
    }
}
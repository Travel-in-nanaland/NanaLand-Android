package com.jeju.nanaland.ui.component.signup.typetestcompletion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.ui.theme.largeTitle02Regular
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestCompletionScreenText() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = largeTitle02.toSpanStyle().copy(
                        color = getColor().main
                    )
                ) {
                    append(UserData.nickname)
                }
                withStyle(
                    style = largeTitle02.toSpanStyle().copy(
                        color = getColor().black
                    )
                ) {
                    append(getString(R.string.type_test_screen_님의))
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = getString(R.string.type_test_screen_text1),
            color = getColor().black,
            style = largeTitle02Regular
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = getString(R.string.type_test_screen_text2),
            color = getColor().black,
            style = largeTitle02Regular
        )
    }
}
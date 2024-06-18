package com.jeju.nanaland.ui.component.signup.recommendedspot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.language.customContext
import com.jeju.nanaland.util.resource.getString

@Composable
fun RecommendedSpotScreenText1() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = when (customContext.resources.configuration.locales[0].language) {
            "ko" -> UserData.nickname + getString(
                R.string.type_test_recommended_spot_text2)
            "ms" -> getString(R.string.type_test_recommended_spot_text1) + UserData.nickname + getString(R.string.type_test_recommended_spot_text2)
            "zh" -> ""
            else -> getString(R.string.type_test_recommended_spot_text2)
        },
        color = getColor().black,
        style = largeTitle01,
        textAlign = TextAlign.Center
    )
}
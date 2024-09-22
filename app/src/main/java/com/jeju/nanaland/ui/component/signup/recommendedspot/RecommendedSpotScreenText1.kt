package com.jeju.nanaland.ui.component.signup.recommendedspot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString

@Composable
fun RecommendedSpotScreenText1() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = getString(R.string.type_test_recommended_spot_text, UserData.nickname),
        color = getColor().black,
        style = title02,
        textAlign = TextAlign.Center
    )
}
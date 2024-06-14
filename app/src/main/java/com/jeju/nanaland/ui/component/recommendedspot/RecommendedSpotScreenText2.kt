package com.jeju.nanaland.ui.component.recommendedspot

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.resource.getString

@Composable
fun RecommendedSpotScreenText2() {
    Text(
        text = getString(R.string.type_test_recommended_spot_text2),
        color = getColor().main,
        style = largeTitle01
    )
}
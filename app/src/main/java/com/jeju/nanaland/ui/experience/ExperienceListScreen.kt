package com.jeju.nanaland.ui.experience

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun ExperienceListScreen(
    moveToBackScreen: () -> Unit,
) {
    ExperienceListScreen(
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ExperienceListScreen(
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Column(Modifier.fillMaxSize()) {
            CustomTopBar(
                title = getString(R.string.common_이색_체험),
                onBackButtonClicked = moveToBackScreen
            )

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
                        text = getString(R.string.search_result_screen_preparing_service),
                        color = getColor().gray01,
                        style = body01,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceListScreenPreview() {

}
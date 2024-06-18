package com.jeju.nanaland.ui.notification

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
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomTopBarNoBackButton
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun NotificationScreen() {
    NotificationScreen(
        isContent = true
    )
}

@Composable
private fun NotificationScreen(
    isContent: Boolean
) {
    Column(Modifier.fillMaxSize()) {
        CustomTopBarNoBackButton(title = getString(R.string.common_제주_이야기))

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
package com.jeju.nanaland.ui.component.languageinitialization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun LanguageInitializationScreenLanguageBox(
    text1: String,
    text2: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(115.dp)
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(12.dp)
            )
            .clickableNoEffect { onClick() }
            .padding(12.dp)
    ) {
        Text(
            text = text1,
            color = getColor().black,
            style = body02Bold
        )

        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text2,
                color = getColor().black,
                style = caption01
            )

            Spacer(Modifier.width(4.dp))

            Image(
                modifier = Modifier.width(16.dp),
                painter = painterResource(R.drawable.ic_right_arrow_2),
                contentDescription = null
            )
        }
    }
}
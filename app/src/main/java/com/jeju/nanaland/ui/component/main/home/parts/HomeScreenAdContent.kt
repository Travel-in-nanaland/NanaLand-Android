package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun HomeScreenAdContent(
    idx: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = when (idx % 4) {
                    0 -> getColor().skyblue
                    1 -> getColor().main50
                    2 -> getColor().pink
                    else -> getColor().yellow
                }
            )
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                HomeScreenAdText1(idx = idx)

                Spacer(Modifier.height(4.dp))

                HomeScreenAdText2(idx = idx)
            }

            Spacer(Modifier.weight(1f))

            if (getLanguage() == LanguageType.Korean || getLanguage() == LanguageType.Chinese) {
                HomeScreenAdImage(idx = idx)
            }
        }
    }
}
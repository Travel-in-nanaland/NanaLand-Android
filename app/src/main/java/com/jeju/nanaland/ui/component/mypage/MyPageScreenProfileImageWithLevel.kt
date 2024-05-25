package com.jeju.nanaland.ui.component.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.mypage.parts.MyPageScreenLevel
import com.jeju.nanaland.ui.component.mypage.parts.MyPageScreenProfileImage

@Composable
fun ColumnScope.MyPageScreenProfileImageWithLevel(
    imageUri: String,
    level: Long
) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(108.dp)
    ) {
        MyPageScreenProfileImage(imageUri = imageUri)

        MyPageScreenLevel(level = level)
    }
}
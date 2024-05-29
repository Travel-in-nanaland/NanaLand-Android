package com.jeju.nanaland.ui.component.profileupdate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.profileupdate.parts.ProfileUpdateScreenProfileImage
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ColumnScope.ProfileUpdateScreenProfileContent(
    imageUri: String?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.BottomEnd
    ) {
        ProfileUpdateScreenProfileImage(imageUri = imageUri)

        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(R.drawable.img_photo_preview),
            contentDescription = null
        )
    }
}